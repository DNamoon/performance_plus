package com.starter.performance.service.dto;

import com.starter.performance.domain.Artist;
import com.starter.performance.domain.Performance;
import com.starter.performance.domain.PerformanceSchedule;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindPerformanceResponseServiceDto {

    private Long id;
    private String name;
    private String venue;
    private String detail;
    private String imageUrl;
    private List<FindPerformanceScheduleResponseServiceDto> performanceSchedules = new ArrayList<>();
    private List<FindArtistResponseServiceDto> artists = new ArrayList<>();

    @Builder
    public FindPerformanceResponseServiceDto(Long id, String name, String venue, String detail, String imageUrl,
        List<FindPerformanceScheduleResponseServiceDto> performanceSchedules,
        List<FindArtistResponseServiceDto> artists) {
        this.id = id;
        this.name = name;
        this.venue = venue;
        this.detail = detail;
        this.imageUrl = imageUrl;
        this.performanceSchedules = performanceSchedules;
        this.artists = artists;
    }

    public static FindPerformanceResponseServiceDto of(Performance performance,
        List<PerformanceSchedule> performanceSchedules,
        List<Artist> artists) {

        return FindPerformanceResponseServiceDto.builder()
            .id(performance.getId())
            .name(performance.getName())
            .venue(performance.getVenue())
            .detail(performance.getDetail())
            .imageUrl(performance.getImageUrl())
            .performanceSchedules(
                performanceSchedules.stream()
                    .map(FindPerformanceScheduleResponseServiceDto::of)
                    .collect(Collectors.toList()))
            .artists(
                artists.stream()
                    .map(FindArtistResponseServiceDto::of)
                    .collect(Collectors.toList()))
            .build();
    }

    public static FindPerformanceResponseServiceDto of(Performance performance) {

        return FindPerformanceResponseServiceDto.builder()
            .id(performance.getId())
            .name(performance.getName())
            .venue(performance.getVenue())
            .detail(performance.getDetail())
            .imageUrl(performance.getImageUrl())
            .performanceSchedules(
                performance.getPerformanceSchedules().stream()
                    .map(FindPerformanceScheduleResponseServiceDto::of)
                    .collect(Collectors.toList()))
            .artists(
                performance.getArtists().stream()
                    .map(FindArtistResponseServiceDto::of)
                    .collect(Collectors.toList()))
            .build();
    }
}

