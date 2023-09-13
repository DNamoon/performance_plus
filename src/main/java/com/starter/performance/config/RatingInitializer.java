package com.starter.performance.config;

import com.starter.performance.domain.Rating;
import com.starter.performance.domain.RatingName;
import com.starter.performance.repository.RatingRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingInitializer {

    private final RatingRepository ratingRepository;

    @PostConstruct
    public void init() {
        Rating standardRating = new Rating();
        standardRating.setName(RatingName.STANDARD);
        ratingRepository.save(standardRating);

        Rating vipRating = new Rating();
        vipRating.setName(RatingName.VIP);
        ratingRepository.save(vipRating);
    }
}
