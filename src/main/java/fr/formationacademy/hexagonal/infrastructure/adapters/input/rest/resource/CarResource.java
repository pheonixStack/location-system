package fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.resource;

import fr.formationacademy.hexagonal.application.ports.input.CarUseCase;
import fr.formationacademy.hexagonal.application.ports.input.InspectionUseCase;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.CarRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.PlanInspectionRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.UpdateCarStatusRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.UrgentInspectionRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response.CarProfitabilityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.formationacademy.hexagonal.shared.Constants.APP_ROOT;


@RestController
@RequestMapping(APP_ROOT + "/cars")
@Validated
public class CarResource {

    private final CarUseCase carUseCase;
    private final InspectionUseCase inspectionUseCase;

    public CarResource(CarUseCase carUseCase, InspectionUseCase inspectionUseCase) {
        this.carUseCase = carUseCase;
        this.inspectionUseCase = inspectionUseCase;
    }

    @Operation(summary = "Créer une nouvelle voiture")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Voiture créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody CarRequest request) {
        carUseCase.createCar(request);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Mettre à jour le statut d'une voiture")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Statut mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Voiture non trouvée")
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateCarStatus(@PathVariable Long id, @RequestBody UpdateCarStatusRequest request) {
        request.setCarId(id);
        carUseCase.updateStatus(request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Planifier une inspection pour une voiture")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Inspection planifiée avec succès"),
            @ApiResponse(responseCode = "404", description = "Voiture non trouvée")
    })
    @PostMapping("/{id}/inspection")
    public ResponseEntity<Void> planCarInspection(@PathVariable Long id, @RequestBody PlanInspectionRequest request) {
        request.setCarId(id);
        inspectionUseCase.planInspection(request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lister les inspections urgentes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste récupérée avec succès")
    })
    @GetMapping("/inspection")
    public ResponseEntity<List<UrgentInspectionRequest>> listUrgentInspections() {
        List<UrgentInspectionRequest> urgentCars = inspectionUseCase.listUrgentCars();
        return ResponseEntity.ok(urgentCars);
    }

    @Operation(summary = "Obtenir la rentabilité d'une voiture")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rentabilité récupérée avec succès"),
            @ApiResponse(responseCode = "404", description = "Voiture non trouvée")
    })
    @GetMapping("/{carId}/profitability")
    public ResponseEntity<CarProfitabilityResponse> getCarProfitability(@PathVariable Long carId) {
        CarProfitabilityResponse response = carUseCase.calculate(carId);
        return ResponseEntity.ok(response);
    }
}
