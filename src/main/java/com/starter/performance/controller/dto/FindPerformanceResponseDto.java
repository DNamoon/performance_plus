package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.FindPerformanceResponseServiceDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindPerformanceResponseDto {

    private Long performanceId;
    private String name;
    private String venue;
    private String detail;
    private String imageUrl;
    private List<FindPerformanceScheduleResponseDto> performanceSchedules = new ArrayList<>();
    private List<FindArtistResponseDto> artists = new ArrayList<>();

    @Builder
    private FindPerformanceResponseDto(Long performanceId, String name, String venue, String detail, String imageUrl,
        List<FindPerformanceScheduleResponseDto> performanceSchedules, List<FindArtistResponseDto> artists) {
        this.performanceId = performanceId;
        this.name = name;
        this.venue = venue;
        this.detail = detail;
        this.imageUrl = imageUrl;
        this.performanceSchedules = performanceSchedules;
        this.artists = artists;
    }

    public static FindPerformanceResponseDto of(FindPerformanceResponseServiceDto performance) {

        return FindPerformanceResponseDto.builder()
            .performanceId(performance.getId())
            .name(performance.getName())
            .venue(performance.getVenue())
            .detail(performance.getDetail())
            .imageUrl(performance.getImageUrl())
            .performanceSchedules(
                performance.getPerformanceSchedules()
                    .stream()
                    .map(FindPerformanceScheduleResponseDto::of)
                    .collect(Collectors.toList()))
            .artists(
                performance.getArtists()
                    .stream()
                    .map(FindArtistResponseDto::of)
                    .collect(Collectors.toList()))
            .build();
    }
}
