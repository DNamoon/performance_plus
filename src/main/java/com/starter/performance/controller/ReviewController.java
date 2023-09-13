package com.starter.performance.controller;

import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.controller.dto.ReviewRequestDto;
import com.starter.performance.service.ReviewService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/reservations/reviews")
    public ResponseEntity<ResponseDto> viewReviews(Authentication auth, Pageable pageable) {
        ResponseDto responseDto = reviewService.showReviews(auth, pageable);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/reservations/reviews/{reviewId}")
    public ResponseEntity<ResponseDto> deleteReview(Authentication auth,
        @PathVariable Long reviewId) {
        ResponseDto responseDto = reviewService.deleteReview(auth, reviewId);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/reservations/reviews/{reviewId}")
    public ResponseEntity<ResponseDto> changeReview(@RequestBody @Valid ReviewRequestDto dto,
        @PathVariable Long reviewId, Authentication auth) {
        ResponseDto responseDto = reviewService.changeReview(dto, reviewId, auth);
        return ResponseEntity.ok(responseDto);
    }
}
