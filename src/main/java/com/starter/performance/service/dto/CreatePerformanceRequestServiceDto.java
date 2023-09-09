package com.starter.performance.service.dto;

import com.starter.performance.domain.Performance;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePerformanceRequestServiceDto {

    private String performanceName;
    private String venue;
    private String detail;
    private String imageUrl;

    @Builder
    private CreatePerformanceRequestServiceDto(String performanceName, String venue, String detail,
        String imageUrl) {
        this.performanceName = performanceName;
        this.venue = venue;
        this.detail = detail;
        this.imageUrl = imageUrl;
    }

    public Performance toEntity() {
        return Performance.builder()
            .name(this.performanceName)
            .venue(this.venue)
            .detail(this.detail)
            .imageUrl(this.imageUrl)
            .build();
    }
}

