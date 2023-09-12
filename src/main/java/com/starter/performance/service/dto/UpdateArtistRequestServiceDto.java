package com.starter.performance.service.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateArtistRequestServiceDto {

    private Long artistId;
    private Long performanceId;
    private String artistName;

    @Builder
    private UpdateArtistRequestServiceDto(Long artistId, Long performanceId, String artistName) {
        this.artistId = artistId;
        this.performanceId = performanceId;
        this.artistName = artistName;
    }
}

