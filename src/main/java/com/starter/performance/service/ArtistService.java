package com.starter.performance.service;

import com.starter.performance.service.dto.CreateArtistRequestServiceDto;
import com.starter.performance.service.dto.CreateArtistResponseServiceDto;

public interface ArtistService {

    CreateArtistResponseServiceDto create(Long performanceId,
        CreateArtistRequestServiceDto createArtistRequestServiceDto);
}
