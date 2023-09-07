package com.starter.performance.repository;

import com.starter.performance.domain.Member;
import com.starter.performance.domain.PerformanceSchedule;
import com.starter.performance.domain.Reservation;
import com.starter.performance.domain.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByMemberAndPerformanceScheduleAndReservationStatus(Member member,
        PerformanceSchedule performanceSchedule, ReservationStatus reservationStatus);
}
