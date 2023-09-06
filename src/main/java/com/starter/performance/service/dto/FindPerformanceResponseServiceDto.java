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
    private List<FindPerformanceScheduleResponseServiceDto> performanceSchedules = new ArrayList<>();
    private List<FindArtistResponseServiceDto> artists = new ArrayList<>();

    @Builder
    private FindPerformanceResponseServiceDto(Long id, String name, String venue, String detail,
        List<FindPerformanceScheduleResponseServiceDto> performanceSchedules,
        List<FindArtistResponseServiceDto> artists) {
        this.id = id;
        this.name = name;
        this.venue = venue;
        this.detail = detail;
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
}
