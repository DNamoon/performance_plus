package com.starter.performance.batch.config;

import com.starter.performance.batch.dto.PerformanceScheduleDto;
import com.starter.performance.batch.reader.CustomJpaItemReader;
import com.starter.performance.domain.PerformanceStatus;
import com.starter.performance.repository.PerformanceScheduleRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@EnableBatchProcessing
public class PerformanceStatusBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final PerformanceScheduleRepository performanceScheduleRepository;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("performanceStatusJob")
            .start(standByToTicketingStep())
            .next(ticketingToProceedingStep())
            .next(proceedingToEndStep())
            .build();
    }

    @Bean
    public Step standByToTicketingStep() throws Exception {
        return stepBuilderFactory.get("standByToTicketingStep")
            .<PerformanceScheduleDto, PerformanceScheduleDto>chunk(30)
            .reader(standByStatusReader(null))
            .writer(ticketingStatusWriter())
            .build();
    }

    @Bean
    public Step ticketingToProceedingStep() throws Exception {
        return stepBuilderFactory.get("ticketingToProceedingStep")
            .<PerformanceScheduleDto, PerformanceScheduleDto>chunk(30)
            .reader(ticketingStatusReader(null))
            .writer(proceedingStatusWriter())
            .build();
    }

    @Bean
    public Step proceedingToEndStep() throws Exception {
        return stepBuilderFactory.get("ticketingToProceedingStep")
            .<PerformanceScheduleDto, PerformanceScheduleDto>chunk(30)
            .reader(proceedingStatusReader(null))
            .writer(endStatusWriter())
            .build();
    }

    @Bean
    @StepScope
    public AbstractPagingItemReader<? extends PerformanceScheduleDto> standByStatusReader(
        @Value("#{jobParameters['now']}") Date date) throws Exception {

        Map<String, Object> parameters = new HashMap<>();
        LocalDate now = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ticketingDay = now.plusDays(7);

        parameters.put("ticketingDay", ticketingDay);
        parameters.put("performanceStatus", PerformanceStatus.STAND_BY);

        CustomJpaItemReader<PerformanceScheduleDto> reader = new CustomJpaItemReader<>();
        reader.setPageSize(30);
        reader.setQueryString(
            "select new com.starter.performance.batch.dto.PerformanceScheduleDto(ps.id, ps.performanceStatus) "
                + "from PerformanceSchedule ps "
                + "where function('DATE', ps.performanceDate) = function('DATE', :ticketingDay) "
                + "and ps.performanceStatus = :performanceStatus");
        reader.setParameterValues(parameters);
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.afterPropertiesSet();
        return reader;
    }

    @Bean
    @StepScope
    public AbstractPagingItemReader<? extends PerformanceScheduleDto> ticketingStatusReader(
        @Value("#{jobParameters['now']}") Date date) throws Exception {

        Map<String, Object> parameters = new HashMap<>();
        LocalDate now = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        parameters.put("currentDate", now);
        parameters.put("performanceStatus", PerformanceStatus.TICKETING);

        CustomJpaItemReader<PerformanceScheduleDto> reader = new CustomJpaItemReader<>();
        reader.setPageSize(30);
        reader.setQueryString(
            "select new com.starter.performance.batch.dto.PerformanceScheduleDto(ps.id, ps.performanceStatus) "
                + "from PerformanceSchedule ps "
                + "where function('DATE', ps.performanceDate) = function('DATE', :currentDate) "
                + "and ps.performanceStatus = :performanceStatus");
        reader.setParameterValues(parameters);
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.afterPropertiesSet();
        return reader;
    }

    @Bean
    @StepScope
    public AbstractPagingItemReader<? extends PerformanceScheduleDto> proceedingStatusReader(
        @Value("#{jobParameters['now']}") Date date) throws Exception {

        Map<String, Object> parameters = new HashMap<>();
        LocalDate now = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = now.minusDays(1);

        parameters.put("endDate", endDate);
        parameters.put("performanceStatus", PerformanceStatus.PROCEEDING);

        CustomJpaItemReader<PerformanceScheduleDto> reader = new CustomJpaItemReader<>();
        reader.setPageSize(30);
        reader.setQueryString(
            "select new com.starter.performance.batch.dto.PerformanceScheduleDto(ps.id, ps.performanceStatus) "
                + "from PerformanceSchedule ps "
                + "where function('DATE', ps.performanceDate) = function('DATE', :endDate) "
                + "and ps.performanceStatus = :performanceStatus");
        reader.setParameterValues(parameters);
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.afterPropertiesSet();
        return reader;
    }

    @Bean
    public ItemWriter<PerformanceScheduleDto> ticketingStatusWriter() {
        return items -> performanceScheduleRepository.updateStatus(
            items.stream().map(PerformanceScheduleDto::getId).collect(Collectors.toList()),
            PerformanceStatus.TICKETING
        );
    }

    @Bean
    public ItemWriter<PerformanceScheduleDto> proceedingStatusWriter() {
        return items -> performanceScheduleRepository.updateStatus(
            items.stream().map(PerformanceScheduleDto::getId).collect(Collectors.toList()),
            PerformanceStatus.PROCEEDING
        );
    }

    @Bean
    public ItemWriter<PerformanceScheduleDto> endStatusWriter() {
        return items -> performanceScheduleRepository.updateStatus(
            items.stream().map(PerformanceScheduleDto::getId).collect(Collectors.toList()),
            PerformanceStatus.END
        );
    }
}

