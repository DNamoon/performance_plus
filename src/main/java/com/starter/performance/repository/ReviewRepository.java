package com.starter.performance.repository;

import com.starter.performance.domain.Member;
import com.starter.performance.domain.Reservation;
import com.starter.performance.domain.Review;
import com.starter.performance.domain.ReviewStatus;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByReservationAndMemberAndReviewStatus
        (Reservation reservation, Member member, ReviewStatus reviewStatus);
    Page<Review> findAllByMember(Member member, Pageable pageable);

}
