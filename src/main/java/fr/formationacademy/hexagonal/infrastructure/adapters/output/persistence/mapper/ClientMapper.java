package fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.mapper;


import fr.formationacademy.hexagonal.domain.model.Client;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.ClientRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientRequest request);

}