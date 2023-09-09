package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.CreatePerformanceResponseServiceDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePerformanceResponseDto {

    private Long performanceId;

    @Builder
    private CreatePerformanceResponseDto(Long performanceId) {
        this.performanceId = performanceId;
    }

    public static CreatePerformanceResponseDto of(CreatePerformanceResponseServiceDto response) {
        return CreatePerformanceResponseDto.builder()
            .performanceId(response.getPerformanceId())
            .build();
    }
}
