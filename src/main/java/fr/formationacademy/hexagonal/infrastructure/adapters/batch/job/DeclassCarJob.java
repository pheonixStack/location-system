package fr.formationacademy.hexagonal.infrastructure.adapters.batch.job;

import fr.formationacademy.hexagonal.application.ports.output.CarPort;
import fr.formationacademy.hexagonal.domain.model.Car;
import fr.formationacademy.hexagonal.domain.model.enums.CarStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DeclassCarJob {

    private final CarPort carPort;

    public DeclassCarJob(CarPort carPort) {
        this.carPort = carPort;
    }

    public void declassCars(List<Car> cars) {
        cars.stream()
                .filter(car -> car.getMissedInspections() >= 3)
                .forEach(car -> {
                    car.setStatus(CarStatus.OUT_OF_SERVICE);
                    carPort.save(car);
                    log.info("Car {} has been declassified due to missed inspections", car.getId());
                });
    }
}

