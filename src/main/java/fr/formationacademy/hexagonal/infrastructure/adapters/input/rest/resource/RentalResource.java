package fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.resource;


import fr.formationacademy.hexagonal.application.ports.input.CarUseCase;
import fr.formationacademy.hexagonal.application.ports.input.RentCarUseCase;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.RentalRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.ReturnCarRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.ActiveRentalResponse;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.RentalResponse;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.ReturnCarResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.formationacademy.hexagonal.shared.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/rentals")
@Validated
public class RentalResource {

    private final CarUseCase carUseCase;
    private final RentCarUseCase rentCarUseCase;

    public RentalResource(CarUseCase carUseCase, RentCarUseCase rentCarUseCase) {
        this.carUseCase = carUseCase;
        this.rentCarUseCase = rentCarUseCase;
    }

    @Operation(summary = "Louer une voiture")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Voiture louée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "404", description = "Voiture ou client non trouvé")
    })
    @PostMapping
    public ResponseEntity<RentalResponse> rentCar(@RequestBody RentalRequest request) {
        RentalResponse response = rentCarUseCase.rentCar(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Retourner une voiture louée")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Voiture retournée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "404", description = "Location non trouvée")
    })
    @PostMapping("/returns")
    public ResponseEntity<ReturnCarResponse> returnCar(@RequestBody ReturnCarRequest request) {
        ReturnCarResponse response = carUseCase.returnCar(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lister les locations actives d’un client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès"),
            @ApiResponse(responseCode = "404", description = "Client non trouvé")
    })
    @GetMapping("/customers/{clientId}/rentals")
    public ResponseEntity<List<ActiveRentalResponse>> getActiveRentals(@PathVariable Long clientId) {
        List<ActiveRentalResponse> activeRentals = rentCarUseCase.getActiveRentals(clientId);
        return ResponseEntity.ok(activeRentals);
    }
}