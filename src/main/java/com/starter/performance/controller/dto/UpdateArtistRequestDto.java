package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.UpdateArtistRequestServiceDto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateArtistRequestDto {

    @NotEmpty
    @Size(max = 50)
    private String artistName;

    @Builder
    private UpdateArtistRequestDto(String artistName) {
        this.artistName = artistName;
    }

    public UpdateArtistRequestServiceDto toServiceDto(Long performanceId, Long artistId) {
        return UpdateArtistRequestServiceDto.builder()
            .artistName(this.artistName)
            .performanceId(performanceId)
            .artistId(artistId)
            .build();
    }
}

