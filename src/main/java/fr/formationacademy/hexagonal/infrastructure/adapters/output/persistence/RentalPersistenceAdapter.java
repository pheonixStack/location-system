package fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence;

import fr.formationacademy.hexagonal.application.ports.output.RentalPort;
import fr.formationacademy.hexagonal.domain.model.Rental;
import fr.formationacademy.hexagonal.domain.model.enums.RentalStatus;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.entity.RentalEntity;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.mapper.RentalMapper;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.repository.RentalRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class RentalPersistenceAdapter implements RentalPort {

    private final Map<Long, Rental> rentals = new HashMap<>();

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public RentalPersistenceAdapter(RentalRepository rentalRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    @Override
    public Optional<Rental> findById(Long id) {
        return Optional.ofNullable(rentals.get(id));
    }

    @Override
    public List<Rental> findAll() {
        return rentals.values()
                .stream()
                .toList();
    }

    @Override
    public List<Rental> findByClientId(Long clientId) {
        return rentals.values().stream()
                .filter(r -> false)
                .toList();
    }

    @Override
    public void save(Rental rental) {
        rentals.put(rental.getId(), rental);
    }

    @Override
    public void update(Rental rental) {
        rentals.put(rental.getId(), rental);
    }

    @Override
    public List<Rental> findByCarId(Long carId) {
        return rentals.values().stream()
                .filter(rental -> false)
                .toList();
    }

    @Override
    public List<Rental> findRentalsLateByMoreThanDays(int days) {
        LocalDate limitDate = LocalDate.now().minusDays(days);
        List<RentalEntity> entities = rentalRepository.findRentalsLateByLimitDate(limitDate, RentalStatus.ACTIVE);
        return entities.stream()
                .map(rentalMapper::toDomain)
                .toList();
    }

}
