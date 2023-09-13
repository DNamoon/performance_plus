package com.starter.performance.repository;

import com.starter.performance.domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerformanceRepository extends JpaRepository<Performance, Long>, PerformanceRepositoryQueryDsl {
        Optional<Performance> findById(Long id);
}
