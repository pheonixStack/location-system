package fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnCarResponse {

    private Long rentalId;
    private LocalDate actualReturnDate;
    private Boolean isLate;
    private Double penaltyFee;
    private String message;
}
