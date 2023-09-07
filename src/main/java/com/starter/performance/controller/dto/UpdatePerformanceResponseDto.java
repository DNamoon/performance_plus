package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.UpdatePerformanceResponseServiceDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePerformanceResponseDto {

    private Long performanceId;

    @Builder
    private UpdatePerformanceResponseDto(Long performanceId) {
        this.performanceId = performanceId;
    }

    public static UpdatePerformanceResponseDto of(UpdatePerformanceResponseServiceDto serviceDto) {
        return UpdatePerformanceResponseDto.builder()
            .performanceId(serviceDto.getId())
            .build();
    }
}

