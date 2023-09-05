package com.starter.performance.repository;

import com.starter.performance.domain.Bookmark;
import com.starter.performance.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    boolean existsByMemberIdAndPerformanceId(Long memberId, Long performanceId);
    @Transactional
    void deleteByPerformanceIdAndMember(Long id, Member member);
}
