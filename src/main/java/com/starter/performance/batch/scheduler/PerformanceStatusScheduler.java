package com.starter.performance.batch.scheduler;

import com.starter.performance.batch.config.PerformanceStatusBatchConfiguration;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class PerformanceStatusScheduler {

    private final JobLauncher jobLauncher;
    private final PerformanceStatusBatchConfiguration configuration;

    @Scheduled(cron = "${schedules.cron.schedule-status}")
    public void runJob() {
        JobParameters jobParameters = new JobParametersBuilder()
            .addDate("now", new Date())
            .toJobParameters();

        try {

            jobLauncher.run(configuration.job(), jobParameters);

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
