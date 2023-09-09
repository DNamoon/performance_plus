package com.starter.performance.service.impl;

import com.starter.performance.repository.PerformanceRepository;
import com.starter.performance.service.PerformanceService;
import com.starter.performance.service.dto.CreatePerformanceRequestServiceDto;
import com.starter.performance.service.dto.CreatePerformanceResponseServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;

    @Transactional
    @Override
    public CreatePerformanceResponseServiceDto create(
        CreatePerformanceRequestServiceDto createPerformanceDto) {

        return CreatePerformanceResponseServiceDto.of(
            performanceRepository.save(createPerformanceDto.toEntity())
        );
    }
}
