package com.starter.performance.service.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePerformanceRequestServiceDto {

    private Long id;
    private String name;
    private String venue;
    private String detail;
    private String imageUrl;

    @Builder
    private UpdatePerformanceRequestServiceDto(Long id, String name, String venue, String detail, String imageUrl) {
        this.id = id;
        this.name = name;
        this.venue = venue;
        this.detail = detail;
        this.imageUrl = imageUrl;
    }
}

