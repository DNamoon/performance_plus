package com.starter.performance.service.dto;

import com.starter.performance.domain.PerformanceSchedule;
import com.starter.performance.domain.PerformanceStatus;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindPerformanceScheduleResponseServiceDto {


    private Long id;
    private LocalDateTime performanceDate;
    private int ticketQuantity;
    private PerformanceStatus performanceStatus;

    @Builder
    private FindPerformanceScheduleResponseServiceDto(Long id, LocalDateTime performanceDate, int ticketQuantity,
        PerformanceStatus performanceStatus) {
        this.id = id;
        this.performanceDate = performanceDate;
        this.ticketQuantity = ticketQuantity;
        this.performanceStatus = performanceStatus;
    }

    public static FindPerformanceScheduleResponseServiceDto of(PerformanceSchedule performanceSchedule) {
        return FindPerformanceScheduleResponseServiceDto.builder()
            .id(performanceSchedule.getId())
            .performanceDate(performanceSchedule.getPerformanceDate())
            .ticketQuantity(performanceSchedule.getTicketQuantity())
            .performanceStatus(performanceSchedule.getPerformanceStatus())
            .build();
    }
}
