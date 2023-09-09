package com.starter.performance.service.impl;

import com.starter.performance.domain.Performance;
import com.starter.performance.repository.ArtistRepository;
import com.starter.performance.repository.PerformanceRepository;
import com.starter.performance.service.ArtistService;
import com.starter.performance.service.dto.CreateArtistRequestServiceDto;
import com.starter.performance.service.dto.CreateArtistResponseServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final PerformanceRepository performanceRepository;

    @Transactional
    @Override
    public CreateArtistResponseServiceDto create(Long performanceId, CreateArtistRequestServiceDto serviceDto) {
        Performance performance = performanceRepository.findById(performanceId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공연 ID" + performanceId));

        return CreateArtistResponseServiceDto.of(
            artistRepository.save(serviceDto.toEntity(performance))
        );
    }
}
