package fr.formationacademy.hexagonal.infrastructure.adapters.output.persistence.entity;

import fr.formationacademy.hexagonal.domain.model.enums.RentalStatus;
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
@Table(name = "rental")
public class RentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientId;
    private String carId;
    private String carModel;
    private LocalDate startDate;
    private LocalDate endDate;
    private RentalStatus status;
}
