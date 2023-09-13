package com.starter.performance.repository.impl;

import static com.starter.performance.domain.QPerformanceSchedule.performanceSchedule;

import com.starter.performance.domain.PerformanceSchedule;
import com.starter.performance.domain.PerformanceStatus;
import com.starter.performance.repository.PerformanceScheduleRepositoryQueryDsl;
import com.starter.performance.repository.QueryDslRepositorySupport;
import java.util.List;

public class PerformanceScheduleRepositoryQueryDslImpl extends QueryDslRepositorySupport implements
    PerformanceScheduleRepositoryQueryDsl {

    public PerformanceScheduleRepositoryQueryDslImpl() {
        super(PerformanceSchedule.class);
    }

    @Override
    public void updateStatus(List<Long> ids, PerformanceStatus performanceStatus) {
        super.getQueryFactory().update(performanceSchedule)
            .set(performanceSchedule.performanceStatus, performanceStatus)
            .where(performanceSchedule.id.in(ids))
            .execute();
    }
}
