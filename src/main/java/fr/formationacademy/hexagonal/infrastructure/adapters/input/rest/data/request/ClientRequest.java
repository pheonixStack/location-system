package fr.formationacademy.hexagonal.infrastructure.adapters.input.rest.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequest {

    private Long id;
    private String name;
    private boolean hasUnpaidDebt;
    private boolean hasBlockedDeposit;
}
