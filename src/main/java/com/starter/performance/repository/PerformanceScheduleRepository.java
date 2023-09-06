package com.starter.performance.repository;

import com.starter.performance.domain.Performance;
import com.starter.performance.domain.PerformanceSchedule;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PerformanceScheduleRepository extends JpaRepository<PerformanceSchedule, Long> {

    Optional<PerformanceSchedule> findByPerformance(Performance performance);

    List<PerformanceSchedule> findAllByPerformance(Performance performance);
}
