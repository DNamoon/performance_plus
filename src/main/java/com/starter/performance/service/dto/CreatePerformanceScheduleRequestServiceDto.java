package com.starter.performance.service.dto;

import com.starter.performance.domain.Performance;
import com.starter.performance.domain.PerformanceSchedule;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePerformanceScheduleRequestServiceDto {

    private LocalDateTime performanceDate;
    private int ticketQuantity;

    @Builder
    private CreatePerformanceScheduleRequestServiceDto(LocalDateTime performanceDate, int ticketQuantity) {
        this.performanceDate = performanceDate;
        this.ticketQuantity = ticketQuantity;
    }

    public PerformanceSchedule toEntity(Performance performance) {
        return PerformanceSchedule.builder()
            .performanceDate(performanceDate)
            .ticketQuantity(ticketQuantity)
            .performance(performance)
            .build();
    }


}
