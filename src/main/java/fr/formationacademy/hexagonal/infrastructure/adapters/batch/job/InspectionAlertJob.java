package fr.formationacademy.hexagonal.infrastructure.adapters.batch.job;

import fr.formationacademy.hexagonal.application.ports.output.CarPort;
import fr.formationacademy.hexagonal.domain.model.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class InspectionAlertJob {

    private final CarPort carPort;

    public InspectionAlertJob(CarPort carPort) {
        this.carPort = carPort;
    }

    public List<Car> findCarsToInspect() {
        List<Car> carsToInspect = carPort.findCarsToInspectWithinDays(7);
        if (!carsToInspect.isEmpty()) {
            log.info("Alert: Cars to inspect soon: {}", carsToInspect.size());
        }
        return carsToInspect;
    }
}

