package com.starter.performance.service.dto;

import com.starter.performance.domain.PerformanceSchedule;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePerformanceScheduleResponseServiceDto {

    private Long performanceScheduleId;

    @Builder
    private UpdatePerformanceScheduleResponseServiceDto(Long performanceScheduleId) {
        this.performanceScheduleId = performanceScheduleId;
    }

    public static UpdatePerformanceScheduleResponseServiceDto of(PerformanceSchedule performanceSchedule) {
        return UpdatePerformanceScheduleResponseServiceDto.builder()
            .performanceScheduleId(performanceSchedule.getId())
            .build();
    }
}

