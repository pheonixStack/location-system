package fr.formationacademy.hexagonal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    private Long id;
    private String name;
    private boolean hasUnpaidDebt;
    private boolean hasBlockedDeposit;

}
