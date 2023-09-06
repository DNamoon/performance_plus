package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.FindArtistResponseServiceDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindArtistResponseDto {

    private Long artistId;

    private String name;


    @Builder
    public FindArtistResponseDto(Long artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }

    public static FindArtistResponseDto of(FindArtistResponseServiceDto artist) {
        return FindArtistResponseDto.builder()
            .artistId(artist.getId())
            .name(artist.getName())
            .build();
    }
}
