package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.CreateArtistResponseServiceDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateArtistResponseDto {

    private Long artistId;

    @Builder
    private CreateArtistResponseDto(Long artistId) {
        this.artistId = artistId;
    }

    public static CreateArtistResponseDto of(CreateArtistResponseServiceDto serviceDto) {
        return CreateArtistResponseDto.builder()
            .artistId(serviceDto.getArtistId())
            .build();

    }
}
