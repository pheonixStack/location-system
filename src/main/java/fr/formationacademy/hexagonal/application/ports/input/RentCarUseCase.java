package fr.formationacademy.hexagonal.application.ports.input;

import fr.formationacademy.hexagonal.domain.model.Rental;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.RentalRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.ActiveRentalResponse;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.RentalResponse;

import java.util.List;

public interface RentCarUseCase {

    RentalResponse rentCar(RentalRequest rentalRequest);

    List<ActiveRentalResponse> getActiveRentals(Long clientId);

    Rental processRental(Rental rental);

}
