package com.starter.performance.service.impl;

import com.starter.performance.domain.Member;
import com.starter.performance.domain.Rating;
import com.starter.performance.domain.RatingName;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.repository.RatingRepository;
import com.starter.performance.service.RatingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class RatingServiceImpl implements RatingService {

    private final MemberRepository memberRepository;
    private final RatingRepository ratingRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void updateMemberRatings() {
        List<Member> members = memberRepository.findAll();

        for (Member member : members) {
            long reviewCount = member.getReviews().size();

            Rating newRating = null;

            if (reviewCount >= 5) {
                newRating = ratingRepository.findByName(RatingName.VIP);
            } else if (reviewCount <= 3) {
                newRating = ratingRepository.findByName(RatingName.STANDARD);
            }

            member.setRating(newRating);

            memberRepository.save(member);
        }
    }
}
