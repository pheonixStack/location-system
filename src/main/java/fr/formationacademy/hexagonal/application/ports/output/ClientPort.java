package fr.formationacademy.hexagonal.application.ports.output;

import fr.formationacademy.hexagonal.domain.model.Client;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ClientPort {

    Optional<Client> findById(Long id);

    void save(Client client);

}
