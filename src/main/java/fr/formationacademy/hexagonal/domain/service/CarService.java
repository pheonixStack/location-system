package fr.formationacademy.hexagonal.domain.service;

import fr.formationacademy.hexagonal.application.ports.input.CarUseCase;
import fr.formationacademy.hexagonal.application.ports.output.CarPort;
import fr.formationacademy.hexagonal.application.ports.output.RentalPort;
import fr.formationacademy.hexagonal.domain.model.Car;
import fr.formationacademy.hexagonal.domain.model.Rental;
import fr.formationacademy.hexagonal.domain.model.enums.CarStatus;
import fr.formationacademy.hexagonal.domain.model.enums.RentalStatus;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.CarRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.ReturnCarRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.UpdateCarStatusRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.CarProfitabilityResponse;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.ReturnCarResponse;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.mapper.CarMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CarService implements CarUseCase {

    private final CarPort carPort;
    private final RentalPort rentalPort;
    private final CarMapper carMapper;

    public CarService(CarPort carPort, RentalPort rentalPort, CarMapper carMapper) {
        this.carPort = carPort;
        this.rentalPort = rentalPort;
        this.carMapper = carMapper;
    }

    @Override
    public void createCar(CarRequest request) {
        Car car = carMapper.toEntity(request);
        carPort.save(car);
    }

    @Override
    public void updateStatus(UpdateCarStatusRequest request) {
        Car car = carPort.findById(request.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Voiture non trouvée"));

        if (request.getNewStatus() == null) {
            throw new IllegalArgumentException("Statut invalide.");
        }

        car.setStatus(request.getNewStatus());
        carPort.save(car);
    }

    @Override
    public ReturnCarResponse returnCar(ReturnCarRequest returnCarRequest) {
        Rental rental = rentalPort.findById(returnCarRequest.getRentalId())
                .orElseThrow(() -> new IllegalArgumentException("Location non trouvée"));

        if (rental.getStatus() == RentalStatus.RETURNED) {
            throw new IllegalStateException("Cette voiture a déjà été restituée.");
        }

        LocalDate returnDate = LocalDate.parse(returnCarRequest.getReturnDate());
        boolean isLate = returnDate.isAfter(rental.getEndDate().plusDays(3));
        double penalty = 0;

        if (isLate) {
            long daysLate = ChronoUnit.DAYS.between(rental.getEndDate().plusDays(3), returnDate);
            penalty = daysLate * 20.0;
        }

        rental.setStatus(RentalStatus.RETURNED);
        rentalPort.save(rental);

        Car car = carPort.findById(rental.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Voiture introuvable"));

        car.setStatus(CarStatus.AVAILABLE);
        carPort.save(car);

        return ReturnCarResponse.builder()
                .rentalId(rental.getId())
                .actualReturnDate(returnDate)
                .isLate(isLate)
                .penaltyFee(penalty)
                .message(isLate ? "Restitution en retard, pénalité appliquée." : "Restitution effectuée.")
                .build();
    }

    @Override
    public CarProfitabilityResponse calculate(Long carId) {
        List<Rental> rentals = rentalPort.findByCarId(carId);

        if (rentals.isEmpty()) {
            return CarProfitabilityResponse.builder()
                    .carId(carId)
                    .profitabilityPercentage(0)
                    .build();
        }
        int totalRentalDays = rentals.stream()
                .mapToInt(r -> r.getEndDate().minusDays(r.getStartDate().toEpochDay()).getDayOfYear())
                .sum();

        LocalDate firstRentalDate = rentals.stream()
                .map(Rental::getStartDate)
                .min(LocalDate::compareTo)
                .orElse(LocalDate.now());

        int totalPeriodDays = (int) firstRentalDate.until(LocalDate.now()).getDays();
        double profitability = totalPeriodDays == 0 ? 0 :
                ((double) totalRentalDays / totalPeriodDays) * 100;

        return CarProfitabilityResponse.builder()
                .carId(carId)
                .profitabilityPercentage(Math.round(profitability * 100.0) / 100.0)
                .build();
    }

}
