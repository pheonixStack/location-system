package fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.repository;

import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity,Long> {

    @Query("SELECT c FROM CarEntity c WHERE c.lastInspectionDate <= :targetDate")
    List<CarEntity> findCarsToInspect(@Param("targetDate") LocalDate targetDate);

}
