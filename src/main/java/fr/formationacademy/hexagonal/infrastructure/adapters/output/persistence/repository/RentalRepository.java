package fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.repository;

import fr.formationacademy.hexagonal.domain.model.enums.RentalStatus;
import fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<RentalEntity,Long> {

    @Query("SELECT r FROM RentalEntity r WHERE r.endDate < :limitDate AND r.status = :status")
    List<RentalEntity> findRentalsLateByLimitDate(@Param("limitDate") LocalDate limitDate,
                                                  @Param("status") RentalStatus status);



}
