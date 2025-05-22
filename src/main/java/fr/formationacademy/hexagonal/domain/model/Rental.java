package fr.formationacademy.hexagonal.domain.model;

import fr.formationacademy.hexagonal.domain.model.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rental {

    private Long id;
    private Long clientId;
    private Long carId;
    private String carModel;
    private LocalDate startDate;
    private LocalDate endDate;
    private RentalStatus status;

}
