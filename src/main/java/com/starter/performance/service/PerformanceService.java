package com.starter.performance.service;

import com.starter.performance.service.dto.CreatePerformanceRequestServiceDto;
import com.starter.performance.service.dto.CreatePerformanceResponseServiceDto;

public interface PerformanceService {

    CreatePerformanceResponseServiceDto create(CreatePerformanceRequestServiceDto createPerformanceDto);

}
