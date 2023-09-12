package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.UpdatePerformanceRequestServiceDto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePerformanceRequestDto {

    @NotEmpty
    @Size(max = 200)
    private String performanceName;
    @NotEmpty
    @Size(max = 200)
    private String venue;
    @Size(max = 1000)
    private String detail;

    @Builder
    private UpdatePerformanceRequestDto(Long performanceId, String performanceName, String venue, String detail,
        String imageUrl) {
        this.performanceName = performanceName;
        this.venue = venue;
        this.detail = detail;
    }

    public UpdatePerformanceRequestServiceDto toServiceDto(Long performanceId, String imageUrl) {
        return UpdatePerformanceRequestServiceDto.builder()
            .id(performanceId)
            .name(this.performanceName)
            .venue(this.venue)
            .detail(this.detail)
            .imageUrl(imageUrl)
            .build();
    }
}

