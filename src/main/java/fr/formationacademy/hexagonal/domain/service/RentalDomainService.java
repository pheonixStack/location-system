package fr.formationacademy.hexagonal.domain.service;

import fr.formationacademy.hexagonal.domain.model.Car;
import fr.formationacademy.hexagonal.domain.model.Client;
import fr.formationacademy.hexagonal.domain.model.Rental;
import fr.formationacademy.hexagonal.domain.model.enums.CarStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalDomainService {

    public boolean canClientRent(Client client, List<Rental> activeRentals) {
        return !client.isHasUnpaidDebt()
                && !client.isHasBlockedDeposit()
                && activeRentals.size() < 2;
    }

    public boolean canCarBeRented(Car car) {
        long daysSinceInspection = ChronoUnit.DAYS.between(car.getLastInspectionDate(), LocalDate.now());
        return car.getStatus() == CarStatus.AVAILABLE
                && car.getKilometers() < 150_000
                && daysSinceInspection <= 90;
    }

    public boolean isRentalPeriodValid(LocalDate start, LocalDate end) {
        long duration = ChronoUnit.DAYS.between(start, end);
        return duration >= 1 && duration <= 30;
    }

    public boolean hasClientRentedSameModelRecently(List<Rental> pastRentals, String model, int withinDays) {
        return pastRentals.stream()
                .filter(r -> r.getCarModel().equals(model))
                .anyMatch(r -> r.getStartDate().isAfter(LocalDate.now().minusDays(withinDays)));
    }

    public boolean carExceedsConsecutiveRentalLimit(Car car) {
        return car.getConsecutiveRentalDays() > 60;
    }
}