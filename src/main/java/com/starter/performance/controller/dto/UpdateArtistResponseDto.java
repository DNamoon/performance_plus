package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.UpdateArtistResponseServiceDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateArtistResponseDto {

    private Long artistId;

    @Builder
    private UpdateArtistResponseDto(Long artistId) {
        this.artistId = artistId;
    }

    public static UpdateArtistResponseDto of(UpdateArtistResponseServiceDto updateArtist) {

        return UpdateArtistResponseDto.builder()
            .artistId(updateArtist.getArtistId())
            .build();
    }
}

