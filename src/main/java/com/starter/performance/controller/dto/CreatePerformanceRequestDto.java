package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.CreatePerformanceRequestServiceDto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePerformanceRequestDto {

    @NotEmpty
    @Size(max = 200)
    private String performanceName;
    @NotEmpty
    @Size(max = 200)
    private String venue;
    @Size(max = 1000)
    private String detail;

    @Builder
    private CreatePerformanceRequestDto(String performanceName, String venue, String detail) {
        this.performanceName = performanceName;
        this.venue = venue;
        this.detail = detail;
    }

    public CreatePerformanceRequestServiceDto toServiceDto(String imageUrl) {
        return CreatePerformanceRequestServiceDto.builder()
            .performanceName(this.performanceName)
            .venue(this.venue)
            .detail(this.detail)
            .imageUrl(imageUrl)
            .build();
    }
}

