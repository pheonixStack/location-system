package fr.formationacademy.hexagonal.application.ports.output;

import fr.formationacademy.hexagonal.domain.model.Rental;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface RentalPort {

    Optional<Rental> findById(Long id);

    List<Rental> findAll();

    List<Rental> findByClientId(Long clientId);

    void save(Rental rental);

    void update(Rental rental);

    List<Rental> findByCarId(Long carId);

    List<Rental> findRentalsLateByMoreThanDays(int days);


}
