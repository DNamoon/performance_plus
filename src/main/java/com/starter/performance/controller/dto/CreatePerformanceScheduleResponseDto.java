package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.CreatePerformanceScheduleResponseServiceDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePerformanceScheduleResponseDto {

    private Long performanceScheduleId;

    @Builder
    private CreatePerformanceScheduleResponseDto(Long performanceScheduleId) {
        this.performanceScheduleId = performanceScheduleId;
    }

    public static CreatePerformanceScheduleResponseDto of(
        CreatePerformanceScheduleResponseServiceDto serviceDto) {

        return CreatePerformanceScheduleResponseDto.builder()
            .performanceScheduleId(serviceDto.getPerformanceScheduleId())
            .build();
    }
}
