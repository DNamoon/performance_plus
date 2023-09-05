package com.starter.performance.controller;

import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.controller.dto.ReviewRequestDto;
import com.starter.performance.service.ReviewService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reservations/reviews/{reservationId}")
    public ResponseEntity<ResponseDto> createReviewV2(@RequestBody @Valid ReviewRequestDto reviewRequestDto,
        @PathVariable Long reservationId, Authentication auth) {

        ResponseDto responseDto = reviewService.registerReview(reviewRequestDto, reservationId, auth);

        return ResponseEntity.ok(responseDto);
    }

}
