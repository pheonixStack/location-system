package fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.mapper;

import fr.formationacademy.hexagonal.domain.model.Client;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.ClientRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T17:48:07+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client toEntity(ClientRequest request) {
        if ( request == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.id( request.getId() );
        client.name( request.getName() );
        client.hasUnpaidDebt( request.isHasUnpaidDebt() );
        client.hasBlockedDeposit( request.isHasBlockedDeposit() );

        return client.build();
    }
}
