package com.starter.performance.controller.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindPerformanceRequestDto {

    private Long performanceId;

    @Builder
    private FindPerformanceRequestDto(Long performanceId) {
        this.performanceId = performanceId;
    }
}
