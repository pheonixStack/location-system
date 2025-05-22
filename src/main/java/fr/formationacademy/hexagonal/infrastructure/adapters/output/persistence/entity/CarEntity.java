package fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.entity;

import fr.formationacademy.hexagonal.domain.model.enums.CarStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "car")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private Integer kilometers;
    private CarStatus status;
    private LocalDate lastInspectionDate;
    private int consecutiveRentalDays;
    private int missedInspections;
}
