package fr.formationacademy.hexagonal.application.ports.input;

import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.PlanInspectionRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.UrgentInspectionRequest;

import java.util.List;

public interface InspectionUseCase {

    void planInspection(PlanInspectionRequest request);

    List<UrgentInspectionRequest> listUrgentCars();

}

