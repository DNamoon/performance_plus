package com.starter.performance.repository;

import com.starter.performance.domain.Rating;
import com.starter.performance.domain.RatingName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Rating findByName(RatingName name);
}
