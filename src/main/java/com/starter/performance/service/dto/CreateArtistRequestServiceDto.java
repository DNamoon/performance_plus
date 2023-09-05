package com.starter.performance.service.dto;

import com.starter.performance.domain.Artist;
import com.starter.performance.domain.Performance;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateArtistRequestServiceDto {

    private String artistName;

    @Builder
    private CreateArtistRequestServiceDto(String artistName) {
        this.artistName = artistName;
    }

    public Artist toEntity(Performance performance) {
        return Artist.builder()
            .name(this.artistName)
            .performance(performance)
            .build();
    }
}
