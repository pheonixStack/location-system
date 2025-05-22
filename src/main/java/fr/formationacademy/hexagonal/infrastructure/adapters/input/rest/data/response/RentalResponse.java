package fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalResponse {

    private Long id;
    private String clientId;
    private String carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

}
