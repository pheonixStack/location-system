package fr.formationacademy.hexagonal.infrastructure.adapters.batch.job;

import fr.formationacademy.hexagonal.application.ports.output.RentalPort;
import fr.formationacademy.hexagonal.domain.model.Rental;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class LateReturnJob {

    private final RentalPort rentalPort;

    public LateReturnJob(RentalPort rentalPort) {
        this.rentalPort = rentalPort;
    }

    public List<Rental> findLateRentals() {
        List<Rental> lateRentals = rentalPort.findRentalsLateByMoreThanDays(3);
        if (!lateRentals.isEmpty()) {
            log.info("Alert: Late rentals: {}", lateRentals.size());
        }
        return lateRentals;
    }
}
