package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.CreateArtistRequestServiceDto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateArtistRequestDto {

    @NotEmpty
    @Size(max = 50)
    private String artistName;

    @Builder
    private CreateArtistRequestDto(String artistName) {
        this.artistName = artistName;
    }

    public CreateArtistRequestServiceDto toServiceDto() {
        return CreateArtistRequestServiceDto.builder()
            .artistName(this.artistName)
            .build();
    }
}
