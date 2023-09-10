package com.starter.performance.controller.dto;

import com.starter.performance.domain.PerformanceStatus;
import com.starter.performance.service.dto.FindPerformanceScheduleResponseServiceDto;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindPerformanceScheduleResponseDto {

    private Long performanceScheduleId;
    private LocalDateTime performanceDate;
    private int ticketQuantity;
    private PerformanceStatus performanceStatus;

    @Builder
    private FindPerformanceScheduleResponseDto(Long performanceScheduleId, LocalDateTime performanceDate,
        int ticketQuantity,
        PerformanceStatus performanceStatus) {
        this.performanceScheduleId = performanceScheduleId;
        this.performanceDate = performanceDate;
        this.ticketQuantity = ticketQuantity;
        this.performanceStatus = performanceStatus;
    }

    public static FindPerformanceScheduleResponseDto of(FindPerformanceScheduleResponseServiceDto performanceSchedule) {
        return FindPerformanceScheduleResponseDto.builder()
            .performanceScheduleId(performanceSchedule.getId())
            .performanceDate(performanceSchedule.getPerformanceDate())
            .ticketQuantity(performanceSchedule.getTicketQuantity())
            .performanceStatus(performanceSchedule.getPerformanceStatus())
            .build();
    }
}
