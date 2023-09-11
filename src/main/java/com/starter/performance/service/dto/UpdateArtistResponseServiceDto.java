package com.starter.performance.service.dto;

import com.starter.performance.domain.Artist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateArtistResponseServiceDto {

    private Long artistId;

    @Builder
    private UpdateArtistResponseServiceDto(Long artistId) {
        this.artistId = artistId;
    }

    public static UpdateArtistResponseServiceDto of(Artist artist) {
        return UpdateArtistResponseServiceDto.builder()
            .artistId(artist.getId())
            .build();
    }
}

