package com.starter.performance.service;

import com.starter.performance.controller.dto.ReservationRequestDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.domain.RatingName;
import com.starter.performance.domain.PerformanceSchedule;
import com.starter.performance.domain.Reservation;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

public interface ReservationService {

    @Transactional
    ResponseDto makeReservation(Long performanceId, Long performanceScheduleId, ReservationRequestDto dto,
        Authentication auth);

    // 예매 완료 후 확인 메일 보내기
    void sendMail(String email, Reservation reservation);

    @Transactional
    void updateTicketForStandard(Long performanceScheduleId, Integer ticket);

    @Transactional
    void updateTicket(Long id, Integer ticket, RatingName ratingName);

    void checkPerformance(PerformanceSchedule performanceSchedule, Long performanceId);

    void checkReservationTicketNum(Integer ticket);

    void checkReservationPossibleDate(PerformanceSchedule performanceSchedule, RatingName ratingName);

    void checkPerformanceState(PerformanceSchedule performanceSchedule);

}
