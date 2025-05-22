package fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence;

import fr.formationacademy.hexagonal.application.ports.output.ClientPort;
import fr.formationacademy.hexagonal.domain.model.Client;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ClientPersistenceAdapter implements ClientPort {

    private final Map<Long, Client> clientDatabase = new HashMap<>();

    @Override
    public Optional<Client> findById(Long id) {
        return Optional.ofNullable(clientDatabase.get(id));
    }

    @Override
    public void save(Client client) {
        clientDatabase.put(client.getId(), client);
    }
}
