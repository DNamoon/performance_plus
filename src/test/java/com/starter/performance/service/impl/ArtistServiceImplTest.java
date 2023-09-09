package com.starter.performance.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.starter.performance.domain.Artist;
import com.starter.performance.domain.Performance;
import com.starter.performance.repository.ArtistRepository;
import com.starter.performance.repository.PerformanceRepository;
import com.starter.performance.service.dto.CreateArtistRequestServiceDto;
import com.starter.performance.service.dto.CreateArtistResponseServiceDto;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceImplTest {

    @Mock
    private PerformanceRepository performanceRepository;
    @Mock
    private ArtistRepository artistRepository;
    @InjectMocks
    private ArtistServiceImpl artistService;

    @Test
    @DisplayName("아티스트 등록")
    public void createArtist_success() throws Exception {
        //given
        CreateArtistRequestServiceDto requestServiceDto = CreateArtistRequestServiceDto.builder()
            .artistName("아티스트 이름")
            .build();

        given(performanceRepository.findById(anyLong()))
            .willReturn(Optional.of(Performance.builder()
                .build()));

        given(artistRepository.save(any(Artist.class)))
            .willReturn(Artist.builder()
                .id(1L)
                .build());

        //when
        CreateArtistResponseServiceDto responseServiceDto = artistService.create(1L,
            requestServiceDto);

        //then
        Assertions.assertThat(responseServiceDto.getArtistId())
            .isEqualTo(1L);
    }

    @Test
    @DisplayName("❌ 아티스트 등록시 공연이 존재하지 않으면 예외가 발생한다.")
    public void exception_createArtist_invalidPerformance() throws Exception {
        //given
        CreateArtistRequestServiceDto requestServiceDto = CreateArtistRequestServiceDto.builder()
            .artistName("아티스트 이름")
            .build();

        given(performanceRepository.findById(anyLong()))
            .willReturn(Optional.empty());

        //when //then
        assertThrows(IllegalArgumentException.class,
            () -> artistService.create(1L, requestServiceDto));
    }
}
