package fr.formationacademy.hexagonal.infrastructure.adapters.batch.config;

import fr.formationacademy.hexagonal.domain.model.Car;
import fr.formationacademy.hexagonal.domain.model.Rental;
import fr.formationacademy.hexagonal.infrastructure.adapters.batch.job.DeclassCarJob;
import fr.formationacademy.hexagonal.infrastructure.adapters.batch.job.InspectionAlertJob;
import fr.formationacademy.hexagonal.infrastructure.adapters.batch.job.LateReturnJob;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final DeclassCarJob declassCarJob;
    private final InspectionAlertJob inspectionAlertJob;
    private final LateReturnJob lateReturnJob;

    @Bean
    public Job declassCarBatchJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        Step step = new StepBuilder("declassCarStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    List<Car> carsToCheck = inspectionAlertJob.findCarsToInspect();
                    declassCarJob.declassCars(carsToCheck);
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();

        return new JobBuilder("declassCarBatchJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Job inspectionAlertBatchJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        Step step = new StepBuilder("inspectionAlertStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    inspectionAlertJob.findCarsToInspect();
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();

        return new JobBuilder("inspectionAlertBatchJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Job lateReturnBatchJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        Step step = new StepBuilder("lateReturnStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    List<Rental> lateRentals = lateReturnJob.findLateRentals();
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();

        return new JobBuilder("lateReturnBatchJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
}
