package com.starter.performance.service.dto;

import com.starter.performance.domain.PerformanceSchedule;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePerformanceScheduleResponseServiceDto {

    private Long performanceScheduleId;

    @Builder
    private CreatePerformanceScheduleResponseServiceDto(Long performanceScheduleId) {
        this.performanceScheduleId = performanceScheduleId;
    }

    public static CreatePerformanceScheduleResponseServiceDto of(PerformanceSchedule performanceSchedule) {
        return CreatePerformanceScheduleResponseServiceDto.builder()
            .performanceScheduleId(performanceSchedule.getId())
            .build();
    }
}
