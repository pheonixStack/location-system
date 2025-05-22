package fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request;

import fr.formationacademy.hexagonal.domain.model.enums.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarRequest {

    private Long id;
    private String model;
    private Integer kilometers;
    private CarStatus status;
}
