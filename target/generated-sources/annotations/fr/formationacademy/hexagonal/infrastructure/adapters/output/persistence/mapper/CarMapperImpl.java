package fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.mapper;

import fr.formationacademy.hexagonal.domain.model.Car;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.CarRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.entity.CarEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T17:48:07+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class CarMapperImpl implements CarMapper {

    @Override
    public Car toEntity(CarRequest request) {
        if ( request == null ) {
            return null;
        }

        Car.CarBuilder car = Car.builder();

        car.id( request.getId() );
        car.model( request.getModel() );
        car.kilometers( request.getKilometers() );
        car.status( request.getStatus() );

        return car.build();
    }

    @Override
    public CarEntity toEntity(Car car) {
        if ( car == null ) {
            return null;
        }

        CarEntity.CarEntityBuilder carEntity = CarEntity.builder();

        carEntity.id( car.getId() );
        carEntity.model( car.getModel() );
        carEntity.kilometers( car.getKilometers() );
        carEntity.status( car.getStatus() );
        carEntity.lastInspectionDate( car.getLastInspectionDate() );
        if ( car.getConsecutiveRentalDays() != null ) {
            carEntity.consecutiveRentalDays( car.getConsecutiveRentalDays() );
        }
        if ( car.getMissedInspections() != null ) {
            carEntity.missedInspections( car.getMissedInspections() );
        }

        return carEntity.build();
    }

    @Override
    public Car toDomain(CarEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Car.CarBuilder car = Car.builder();

        car.id( entity.getId() );
        car.model( entity.getModel() );
        car.kilometers( entity.getKilometers() );
        car.status( entity.getStatus() );
        car.lastInspectionDate( entity.getLastInspectionDate() );
        car.consecutiveRentalDays( entity.getConsecutiveRentalDays() );
        car.missedInspections( entity.getMissedInspections() );

        return car.build();
    }
}
