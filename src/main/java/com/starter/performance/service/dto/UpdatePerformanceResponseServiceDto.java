package com.starter.performance.service.dto;

import com.starter.performance.domain.Performance;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePerformanceResponseServiceDto {

    private Long id;
    private String oldImageUrl;

    @Builder
    private UpdatePerformanceResponseServiceDto(Long id, String oldImageUrl) {
        this.id = id;
        this.oldImageUrl = oldImageUrl;
    }

    public static UpdatePerformanceResponseServiceDto of(Performance performance, String oldImageUrl) {
        return UpdatePerformanceResponseServiceDto.builder()
            .id(performance.getId())
            .oldImageUrl(oldImageUrl)
            .build();
    }
}

