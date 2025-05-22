package fr.formationacademy.hexagonal.domain.service;

import fr.formationacademy.hexagonal.application.ports.input.BatchUseCase;
import fr.formationacademy.hexagonal.application.ports.output.CarPort;
import fr.formationacademy.hexagonal.application.ports.output.RentalPort;
import fr.formationacademy.hexagonal.domain.model.Car;
import fr.formationacademy.hexagonal.domain.model.Rental;
import fr.formationacademy.hexagonal.domain.model.enums.CarStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BatchService implements BatchUseCase {

    private final CarPort carPort;
    private final RentalPort rentalPort;

    public BatchService(CarPort carPort, RentalPort rentalPort) {
        this.carPort = carPort;
        this.rentalPort = rentalPort;
    }

    @Override
    public void run() {
        List<Car> carsToInspect = carPort.findCarsToInspectWithinDays(7);
        List<Rental> lateRentals = rentalPort.findRentalsLateByMoreThanDays(3);

        if (!carsToInspect.isEmpty()) {
            log.info("Alert: Cars to inspect soon: {}", carsToInspect.size());
        }
        if (!lateRentals.isEmpty()) {
            log.info("Alert: Late rentals: {}", lateRentals.size());
        }

        carsToInspect.stream()
                .filter(car -> car.getMissedInspections() >= 3)
                .forEach(car -> {
                    car.setStatus(CarStatus.OUT_OF_SERVICE);
                    carPort.save(car);
                    log.info("Car {} has been declassified due to missed inspections", car.getId());
                });
    }
}