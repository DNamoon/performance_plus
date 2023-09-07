package com.starter.performance.repository;

import com.starter.performance.controller.dto.PerformanceSearchConditionDto;
import com.starter.performance.domain.Performance;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PerformanceRepositoryQueryDsl {

    Slice<Performance> findAllByConditions(PerformanceSearchConditionDto performanceSearchConditionDto,
        Pageable pageable);
}

