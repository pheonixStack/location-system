package fr.formationacademy.hexagonal.domain.service;

import fr.formationacademy.hexagonal.application.ports.input.InspectionUseCase;
import fr.formationacademy.hexagonal.application.ports.output.CarPort;
import fr.formationacademy.hexagonal.domain.model.Car;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.PlanInspectionRequest;
import fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request.UrgentInspectionRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InspectionService implements InspectionUseCase {

    private final CarPort carPort;

    public InspectionService(CarPort carPort) {
        this.carPort = carPort;
    }


    public void planInspection(PlanInspectionRequest request) {
        Car car = carPort.findById(request.getCarId())
                .orElseThrow(() -> new IllegalArgumentException("Voiture non trouvée"));

        car.setLastInspectionDate(request.getInspectionDate());
        car.setConsecutiveRentalDays(0);
        car.setMissedInspections(0);

        carPort.save(car);
    }

    public List<UrgentInspectionRequest> listUrgentCars() {
        LocalDate today = LocalDate.now();
        LocalDate deadline = today.plusDays(7);

        return carPort.findAll().stream()
                .filter(car -> car.getLastInspectionDate() != null)
                .filter(car -> car.getLastInspectionDate().plusDays(90).isBefore(deadline))
                .map(car -> UrgentInspectionRequest.builder()
                        .id(car.getId())
                        .model(car.getModel())
                        .lastInspectionDate(car.getLastInspectionDate())
                        .status(car.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
}
