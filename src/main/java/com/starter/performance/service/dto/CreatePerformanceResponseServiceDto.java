package com.starter.performance.service.dto;

import com.starter.performance.domain.Performance;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePerformanceResponseServiceDto {


    private Long performanceId;

    @Builder
    private CreatePerformanceResponseServiceDto(Long performanceId) {
        this.performanceId = performanceId;
    }

    public static CreatePerformanceResponseServiceDto of(Performance performance) {
        return CreatePerformanceResponseServiceDto.builder()
            .performanceId(performance.getId())
            .build();
    }

}
