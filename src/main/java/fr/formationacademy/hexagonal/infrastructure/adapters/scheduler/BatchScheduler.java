package fr.formationacademy.hexagonal.infrastructure.adapters.scheduler;

import fr.formationacademy.hexagonal.application.ports.input.BatchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BatchScheduler {

    private final BatchUseCase batchUseCase;

    @Scheduled(cron = "0 0 2 * * *")
    public void runDailyBatch() {
        batchUseCase.run();
    }
}

