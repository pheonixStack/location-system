package fr.formationacademy.hexagonal.domain.model;

import fr.formationacademy.hexagonal.domain.model.enums.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {

    private Long id;
    private String model;
    private Integer kilometers;
    private CarStatus status;
    private LocalDate lastInspectionDate;
    private Integer consecutiveRentalDays;
    private Integer missedInspections;

}