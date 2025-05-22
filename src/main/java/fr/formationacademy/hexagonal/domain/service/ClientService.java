package fr.formationacademy.hexagonal.domain.service;

import fr.formationacademy.hexagonal.application.ports.input.ClientUseCase;
import fr.formationacademy.hexagonal.application.ports.output.ClientPort;
import fr.formationacademy.hexagonal.domain.model.Client;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.ClientRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.mapper.ClientMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements ClientUseCase {

    private final ClientPort clientPort;
    private final ClientMapper clientMapper;

    public ClientService(ClientPort clientPort, ClientMapper clientMapper) {
        this.clientPort = clientPort;
        this.clientMapper = clientMapper;
    }

    @Override
    public void createClient(ClientRequest request) {
        Client client = clientMapper.toEntity(request);
        clientPort.save(client);
    }

}
