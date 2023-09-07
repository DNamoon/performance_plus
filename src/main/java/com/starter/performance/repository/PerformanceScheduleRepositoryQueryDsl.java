package com.starter.performance.repository;

import com.starter.performance.domain.PerformanceStatus;
import java.util.List;

public interface PerformanceScheduleRepositoryQueryDsl {

    void updateStatus(List<Long> ids, PerformanceStatus performanceStatus);
}
