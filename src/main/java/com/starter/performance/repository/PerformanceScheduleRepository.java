package com.starter.performance.repository;

import com.starter.performance.domain.Performance;
import com.starter.performance.domain.PerformanceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformanceScheduleRepository extends JpaRepository<PerformanceSchedule,Long> {
    Optional<PerformanceSchedule> findByPerformance(Performance performance);
}
