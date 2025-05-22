package fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.mapper;


import fr.formationacademy.hexagonal.domain.model.Rental;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.RentalRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.ActiveRentalResponse;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.RentalResponse;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.entity.RentalEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    Rental toRental(RentalRequest request);

    RentalResponse toRentalResponse(Rental rental);

    Rental toDomain(RentalEntity entity);

    ActiveRentalResponse toActiveRentalRequest(Rental rental);

    List<ActiveRentalResponse> toActiveRentalRequests(List<Rental> rentals);

}
