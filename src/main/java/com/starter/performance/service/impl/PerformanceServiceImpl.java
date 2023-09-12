package com.starter.performance.service.impl;

import com.starter.performance.controller.dto.PerformanceSearchConditionDto;
import com.starter.performance.domain.Artist;
import com.starter.performance.domain.Performance;
import com.starter.performance.domain.PerformanceSchedule;
import com.starter.performance.repository.ArtistRepository;
import com.starter.performance.repository.PerformanceRepository;
import com.starter.performance.repository.PerformanceScheduleRepository;
import com.starter.performance.service.PerformanceService;
import com.starter.performance.service.dto.CreatePerformanceRequestServiceDto;
import com.starter.performance.service.dto.CreatePerformanceResponseServiceDto;
import com.starter.performance.service.dto.FindPerformanceRequestServiceDto;
import com.starter.performance.service.dto.FindPerformanceResponseServiceDto;
import com.starter.performance.service.dto.UpdatePerformanceRequestServiceDto;
import com.starter.performance.service.dto.UpdatePerformanceResponseServiceDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final PerformanceScheduleRepository performanceScheduleRepository;
    private final ArtistRepository artistRepository;

    @Transactional
    @Override
    public CreatePerformanceResponseServiceDto create(
        CreatePerformanceRequestServiceDto createPerformanceDto) {

        return CreatePerformanceResponseServiceDto.of(
            performanceRepository.save(createPerformanceDto.toEntity())
        );
    }

    @Override
    public FindPerformanceResponseServiceDto findPerformance(FindPerformanceRequestServiceDto findPerformanceDto) {
        Performance performance = performanceRepository.findById(findPerformanceDto.getPerformanceId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공연 ID" + findPerformanceDto.getPerformanceId()));

        List<PerformanceSchedule> performanceSchedules = performanceScheduleRepository.findAllByPerformance(
            performance);

        List<Artist> artists = artistRepository.findAllByPerformance(performance);

        return FindPerformanceResponseServiceDto.of(performance, performanceSchedules, artists);
    }

    @Override
    public Slice<FindPerformanceResponseServiceDto> findPerformances(PerformanceSearchConditionDto conditionDto,
        Pageable pageable) {
        return performanceRepository.findAllByConditions(conditionDto, pageable)
            .map(FindPerformanceResponseServiceDto::of);


    }

    @Transactional
    @Override
    public UpdatePerformanceResponseServiceDto updatePerformance(
        UpdatePerformanceRequestServiceDto updatePerformanceDto) {

        Performance performance = performanceRepository.findById(updatePerformanceDto.getId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공연 ID" + updatePerformanceDto.getId()));

        String oldImageUrl = performance.getImageUrl();

        performance.updatePerformance(updatePerformanceDto.getName(), updatePerformanceDto.getVenue(),
            updatePerformanceDto.getDetail(), updatePerformanceDto.getImageUrl());

        return UpdatePerformanceResponseServiceDto.of(performance, oldImageUrl);
    }
}

