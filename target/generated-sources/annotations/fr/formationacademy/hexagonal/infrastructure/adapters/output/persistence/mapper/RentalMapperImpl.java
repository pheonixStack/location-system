package fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.mapper;

import fr.formationacademy.hexagonal.domain.model.Rental;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.RentalRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.ActiveRentalResponse;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.RentalResponse;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.entity.RentalEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T17:48:07+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class RentalMapperImpl implements RentalMapper {

    @Override
    public Rental toRental(RentalRequest request) {
        if ( request == null ) {
            return null;
        }

        Rental.RentalBuilder rental = Rental.builder();

        rental.clientId( request.getClientId() );
        rental.carId( request.getCarId() );
        rental.startDate( request.getStartDate() );
        rental.endDate( request.getEndDate() );

        return rental.build();
    }

    @Override
    public RentalResponse toRentalResponse(Rental rental) {
        if ( rental == null ) {
            return null;
        }

        RentalResponse.RentalResponseBuilder rentalResponse = RentalResponse.builder();

        rentalResponse.id( rental.getId() );
        if ( rental.getClientId() != null ) {
            rentalResponse.clientId( String.valueOf( rental.getClientId() ) );
        }
        if ( rental.getCarId() != null ) {
            rentalResponse.carId( String.valueOf( rental.getCarId() ) );
        }
        rentalResponse.startDate( rental.getStartDate() );
        rentalResponse.endDate( rental.getEndDate() );
        if ( rental.getStatus() != null ) {
            rentalResponse.status( rental.getStatus().name() );
        }

        return rentalResponse.build();
    }

    @Override
    public Rental toDomain(RentalEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Rental.RentalBuilder rental = Rental.builder();

        rental.id( entity.getId() );
        if ( entity.getClientId() != null ) {
            rental.clientId( Long.parseLong( entity.getClientId() ) );
        }
        if ( entity.getCarId() != null ) {
            rental.carId( Long.parseLong( entity.getCarId() ) );
        }
        rental.carModel( entity.getCarModel() );
        rental.startDate( entity.getStartDate() );
        rental.endDate( entity.getEndDate() );
        rental.status( entity.getStatus() );

        return rental.build();
    }

    @Override
    public ActiveRentalResponse toActiveRentalRequest(Rental rental) {
        if ( rental == null ) {
            return null;
        }

        ActiveRentalResponse.ActiveRentalResponseBuilder activeRentalResponse = ActiveRentalResponse.builder();

        if ( rental.getCarId() != null ) {
            activeRentalResponse.carId( String.valueOf( rental.getCarId() ) );
        }
        activeRentalResponse.carModel( rental.getCarModel() );
        activeRentalResponse.startDate( rental.getStartDate() );
        activeRentalResponse.endDate( rental.getEndDate() );
        activeRentalResponse.status( rental.getStatus() );

        return activeRentalResponse.build();
    }

    @Override
    public List<ActiveRentalResponse> toActiveRentalRequests(List<Rental> rentals) {
        if ( rentals == null ) {
            return null;
        }

        List<ActiveRentalResponse> list = new ArrayList<ActiveRentalResponse>( rentals.size() );
        for ( Rental rental : rentals ) {
            list.add( toActiveRentalRequest( rental ) );
        }

        return list;
    }
}
