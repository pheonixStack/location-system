package fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalRequest {

    private Long clientId;
    private Long carId;
    private LocalDate startDate;
    private LocalDate endDate;

}
