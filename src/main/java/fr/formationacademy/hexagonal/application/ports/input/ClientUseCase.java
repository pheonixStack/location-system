package fr.formationacademy.hexagonal.application.ports.input;

import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.ClientRequest;

public interface ClientUseCase {

    void createClient(ClientRequest request);

}
