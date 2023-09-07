package com.starter.performance.service;

import com.starter.performance.controller.dto.PerformanceSearchConditionDto;
import com.starter.performance.service.dto.CreatePerformanceRequestServiceDto;
import com.starter.performance.service.dto.CreatePerformanceResponseServiceDto;
import com.starter.performance.service.dto.FindPerformanceRequestServiceDto;
import com.starter.performance.service.dto.FindPerformanceResponseServiceDto;
import com.starter.performance.service.dto.UpdatePerformanceRequestServiceDto;
import com.starter.performance.service.dto.UpdatePerformanceResponseServiceDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PerformanceService {

    CreatePerformanceResponseServiceDto create(CreatePerformanceRequestServiceDto createPerformanceDto);

    FindPerformanceResponseServiceDto findPerformance(FindPerformanceRequestServiceDto findPerformanceDto);

    Slice<FindPerformanceResponseServiceDto> findPerformances(PerformanceSearchConditionDto conditionDto,
        Pageable pageable);

    UpdatePerformanceResponseServiceDto updatePerformance(UpdatePerformanceRequestServiceDto updatePerformanceDto);
}
