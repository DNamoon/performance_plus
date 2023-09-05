package com.starter.performance.service.dto;

import com.starter.performance.domain.Artist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateArtistResponseServiceDto {

    private Long artistId;

    @Builder
    private CreateArtistResponseServiceDto(Long artistId) {
        this.artistId = artistId;
    }

    public static CreateArtistResponseServiceDto of(Artist artist) {
        return CreateArtistResponseServiceDto.builder()
            .artistId(artist.getId())
            .build();
    }
}
