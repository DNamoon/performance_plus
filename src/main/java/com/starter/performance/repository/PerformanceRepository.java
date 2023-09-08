package com.starter.performance.repository;

import com.starter.performance.domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    Optional<Performance> findByIdAndName(Long Id, String name);
}
