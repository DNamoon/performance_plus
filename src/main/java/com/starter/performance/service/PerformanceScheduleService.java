package com.starter.performance.service;

import com.starter.performance.service.dto.CreatePerformanceScheduleRequestServiceDto;
import com.starter.performance.service.dto.CreatePerformanceScheduleResponseServiceDto;
import com.starter.performance.service.dto.UpdatePerformanceScheduleRequestServiceDto;
import com.starter.performance.service.dto.UpdatePerformanceScheduleResponseServiceDto;

public interface PerformanceScheduleService {

    CreatePerformanceScheduleResponseServiceDto create(Long performanceId,
        CreatePerformanceScheduleRequestServiceDto createPerformanceScheduleServiceDto);

    UpdatePerformanceScheduleResponseServiceDto updatePerformanceSchedule(
        UpdatePerformanceScheduleRequestServiceDto updatePerformanceScheduleServiceDto);
}

