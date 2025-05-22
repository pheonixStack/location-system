package fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.mapper;

import fr.formationacademy.hexagonal.domain.model.Car;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.CarRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.entity.CarEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car toEntity(CarRequest request);

    CarEntity toEntity(Car car);

    Car toDomain(CarEntity entity);

}
