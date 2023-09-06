package com.starter.performance.service.dto;

import com.starter.performance.domain.Artist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindArtistResponseServiceDto {

    private Long id;

    private String name;


    @Builder
    public FindArtistResponseServiceDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static FindArtistResponseServiceDto of(Artist artist) {
        return FindArtistResponseServiceDto.builder()
            .id(artist.getId())
            .name(artist.getName())
            .build();
    }
}
