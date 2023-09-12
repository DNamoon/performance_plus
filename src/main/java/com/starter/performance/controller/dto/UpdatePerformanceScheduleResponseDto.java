package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.UpdatePerformanceScheduleResponseServiceDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePerformanceScheduleResponseDto {

    private Long performanceScheduleId;

    @Builder
    private UpdatePerformanceScheduleResponseDto(Long performanceScheduleId) {
        this.performanceScheduleId = performanceScheduleId;
    }

    public static UpdatePerformanceScheduleResponseDto of(UpdatePerformanceScheduleResponseServiceDto serviceDto) {
        return UpdatePerformanceScheduleResponseDto.builder()
            .performanceScheduleId(serviceDto.getPerformanceScheduleId())
            .build();
    }
}

