package fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request;

import fr.formationacademy.hexagonal.domain.model.enums.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrgentInspectionRequest {

    private Long id;
    private String model;
    private LocalDate lastInspectionDate;
    private CarStatus status;
}
