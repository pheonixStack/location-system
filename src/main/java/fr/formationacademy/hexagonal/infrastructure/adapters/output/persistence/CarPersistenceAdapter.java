package fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence;

import fr.formationacademy.hexagonal.application.ports.output.CarPort;
import fr.formationacademy.hexagonal.domain.model.Car;
import fr.formationacademy.hexagonal.domain.model.enums.CarStatus;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.entity.CarEntity;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.mapper.CarMapper;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.repository.CarRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class CarPersistenceAdapter implements CarPort {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarPersistenceAdapter(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id).map(carMapper::toDomain);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDomain)
                .toList();
    }

    @Override
    public void save(Car car) {
        carRepository.save(carMapper.toEntity(car));
    }

    @Override
    public void updateStatus(Long carId, String status) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + carId));

        car.setStatus(CarStatus.valueOf(status));
        carRepository.save(car);
    }

    @Override
    public List<Car> findCarsToInspectWithinDays(int days) {
        LocalDate targetDate = LocalDate.now().plusDays(days);
        List<CarEntity> entities = carRepository.findCarsToInspect(targetDate);
        return entities.stream()
                .map(carMapper::toDomain)
                .toList();
    }

}

