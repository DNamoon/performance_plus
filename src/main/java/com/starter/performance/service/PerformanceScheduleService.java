package com.starter.performance.service;

import com.starter.performance.service.dto.CreatePerformanceScheduleRequestServiceDto;
import com.starter.performance.service.dto.CreatePerformanceScheduleResponseServiceDto;

public interface PerformanceScheduleService {

    CreatePerformanceScheduleResponseServiceDto create(Long performanceId,
        CreatePerformanceScheduleRequestServiceDto createPerformanceScheduleServiceDto);
}
