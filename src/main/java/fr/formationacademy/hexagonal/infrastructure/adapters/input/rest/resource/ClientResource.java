package fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.resource;

import fr.formationacademy.hexagonal.application.ports.input.ClientUseCase;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.ClientRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fr.formationacademy.hexagonal.shared.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/clients")
@Validated
public class ClientResource {

    private final ClientUseCase clientUseCase;

    public ClientResource(ClientUseCase clientUseCase) {
        this.clientUseCase = clientUseCase;
    }

    @Operation(summary = "Créer un client")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody ClientRequest request) {
        clientUseCase.createClient(request);
        return ResponseEntity.status(201).build();
    }
}

