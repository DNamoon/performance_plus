package com.starter.performance.service;

import com.starter.performance.service.dto.CreatePerformanceRequestServiceDto;
import com.starter.performance.service.dto.CreatePerformanceResponseServiceDto;
import com.starter.performance.service.dto.FindPerformanceRequestServiceDto;
import com.starter.performance.service.dto.FindPerformanceResponseServiceDto;

public interface PerformanceService {

    CreatePerformanceResponseServiceDto create(CreatePerformanceRequestServiceDto createPerformanceDto);

    FindPerformanceResponseServiceDto findPerformance(FindPerformanceRequestServiceDto findPerformanceDto);
}
