package com.starter.performance.service;

import com.starter.performance.service.dto.CreateArtistRequestServiceDto;
import com.starter.performance.service.dto.CreateArtistResponseServiceDto;
import com.starter.performance.service.dto.UpdateArtistRequestServiceDto;
import com.starter.performance.service.dto.UpdateArtistResponseServiceDto;

public interface ArtistService {

    CreateArtistResponseServiceDto create(Long performanceId,
        CreateArtistRequestServiceDto createArtistRequestServiceDto);

    UpdateArtistResponseServiceDto updateArtist(UpdateArtistRequestServiceDto toServiceDto);
}

