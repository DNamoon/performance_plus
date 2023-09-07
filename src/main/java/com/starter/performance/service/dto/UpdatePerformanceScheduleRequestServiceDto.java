package com.starter.performance.service.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePerformanceScheduleRequestServiceDto {

    private Long performanceScheduleId;
    private LocalDateTime performanceDate;
    private int ticketQuantity;

    @Builder
    private UpdatePerformanceScheduleRequestServiceDto(Long performanceScheduleId, LocalDateTime performanceDate,
        int ticketQuantity) {
        this.performanceScheduleId = performanceScheduleId;
        this.performanceDate = performanceDate;
        this.ticketQuantity = ticketQuantity;
    }
}

