package com.starter.performance.service.impl;

import com.starter.performance.domain.Performance;
import com.starter.performance.repository.PerformanceRepository;
import com.starter.performance.repository.PerformanceScheduleRepository;
import com.starter.performance.service.PerformanceScheduleService;
import com.starter.performance.service.dto.CreatePerformanceScheduleRequestServiceDto;
import com.starter.performance.service.dto.CreatePerformanceScheduleResponseServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceScheduleServiceImpl implements PerformanceScheduleService {

    private final PerformanceScheduleRepository performanceScheduleRepository;
    private final PerformanceRepository performanceRepository;

    @Transactional
    @Override
    public CreatePerformanceScheduleResponseServiceDto create(Long performanceId,
        CreatePerformanceScheduleRequestServiceDto serviceDto) {

        Performance performance = performanceRepository.findById(performanceId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공연 ID" + performanceId));

        return CreatePerformanceScheduleResponseServiceDto.of(
            performanceScheduleRepository.save(serviceDto.toEntity(performance))
        );
    }
}
