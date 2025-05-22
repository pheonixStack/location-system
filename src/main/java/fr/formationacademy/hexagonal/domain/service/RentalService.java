package fr.formationacademy.hexagonal.domain.service;

import fr.formationacademy.hexagonal.application.ports.input.RentCarUseCase;
import fr.formationacademy.hexagonal.application.ports.output.CarPort;
import fr.formationacademy.hexagonal.application.ports.output.ClientPort;
import fr.formationacademy.hexagonal.application.ports.output.RentalPort;
import fr.formationacademy.hexagonal.domain.model.Car;
import fr.formationacademy.hexagonal.domain.model.Client;
import fr.formationacademy.hexagonal.domain.model.Rental;
import fr.formationacademy.hexagonal.domain.model.enums.RentalStatus;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.RentalRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.ActiveRentalResponse;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.RentalResponse;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.mapper.RentalMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService implements RentCarUseCase {

    private final ClientPort clientPort;
    private final CarPort carPort;
    private final RentalPort rentalPort;
    private final RentalMapper rentalMapper;
    private final RentalDomainService rentalDomainService;


    public RentalService(ClientPort clientPort, CarPort carPort, RentalPort rentalPort, RentalMapper rentalMapper, RentalDomainService rentalDomainService) {
        this.clientPort = clientPort;
        this.carPort = carPort;
        this.rentalPort = rentalPort;
        this.rentalMapper = rentalMapper;
        this.rentalDomainService = rentalDomainService;
    }

    @Override
    public RentalResponse rentCar(RentalRequest rentalRequest) {

        Client client = clientPort.findById(rentalRequest.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Client non trouvé"));

        Car car = carPort.findById(rentalRequest.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Voiture non trouvée"));

        if (!rentalDomainService.canClientRent(client, rentalPort.findByClientId(rentalRequest.getClientId()))) {
            throw new IllegalArgumentException("Le client ne peut pas louer une autre voiture.");
        }

        if (!rentalDomainService.canCarBeRented(car)) {
            throw new IllegalArgumentException("La voiture n'est pas disponible pour la location.");
        }

        if (!rentalDomainService.isRentalPeriodValid(rentalRequest.getStartDate(), rentalRequest.getEndDate())) {
            throw new IllegalArgumentException("La durée de la location doit être entre 1 et 30 jours.");
        }

        Rental rental = rentalMapper.toRental(rentalRequest);
        rental.setStatus(RentalStatus.ACTIVE);
        rentalPort.save(rental);
        return rentalMapper.toRentalResponse(rental);
    }

    @Override
    public List<ActiveRentalResponse> getActiveRentals(Long clientId) {
        return rentalMapper.toActiveRentalRequests(
                rentalPort.findByClientId(clientId).stream()
                        .filter(rental -> rental.getStatus() == RentalStatus.ACTIVE)
                        .toList()
        );
    }

    @Override
    public Rental processRental(Rental rental) {
        if (rental.getStatus() == RentalStatus.ACTIVE && rental.getEndDate().isBefore(LocalDate.now())) {
            rental.setStatus(RentalStatus.CANCELLED);
        }
        return rental;
    }
}
