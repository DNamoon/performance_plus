package com.starter.performance.batch.dto;

import com.starter.performance.domain.PerformanceStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PerformanceScheduleDto {

    private Long id;
    private PerformanceStatus performanceStatus;

    public PerformanceScheduleDto(Long id, PerformanceStatus performanceStatus) {
        this.id = id;
        this.performanceStatus = performanceStatus;
    }
}
