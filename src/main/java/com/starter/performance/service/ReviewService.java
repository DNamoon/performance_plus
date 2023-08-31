package com.starter.performance.service;

import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.controller.dto.ReviewRequestDto;
import com.starter.performance.domain.Member;
import com.starter.performance.domain.Reservation;
import org.springframework.security.core.Authentication;

public interface ReviewService {

    void checkCanWriteReview(Reservation reservation);

    void checkOneReservation(Member member, Reservation reservation);

    void checkReservationInfo(Member member, Reservation reservation);

    ResponseDto registerReview(ReviewRequestDto reviewDto, Long reservationId, Authentication auth);
}
