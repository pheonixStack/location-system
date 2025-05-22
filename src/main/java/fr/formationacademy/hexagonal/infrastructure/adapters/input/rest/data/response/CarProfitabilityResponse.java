package fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarProfitabilityResponse {

    private Long carId;
    private double profitabilityPercentage;
}