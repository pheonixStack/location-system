package fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.response;

import fr.formationacademy.hexagonal.domain.model.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActiveRentalResponse {

    private Long rentalId;
    private String carId;
    private String carModel;
    private LocalDate startDate;
    private LocalDate endDate;
    private RentalStatus status;
}