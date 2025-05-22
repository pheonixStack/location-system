package fr.formationacademy.hexagonal.application.ports.output;

import fr.formationacademy.hexagonal.domain.model.Car;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CarPort {

    Optional<Car> findById(Long id);

    List<Car> findAll();

    void save(Car car);

    void updateStatus(Long carId, String status);

    List<Car> findCarsToInspectWithinDays(int days);


}
