package com.starter.performance.service.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindPerformanceRequestServiceDto {

    private Long performanceId;

    @Builder
    private FindPerformanceRequestServiceDto(Long performanceId) {
        this.performanceId = performanceId;
    }
}
