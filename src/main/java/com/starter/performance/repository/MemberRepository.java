package com.starter.performance.repository;

import com.starter.performance.domain.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  List<Member> findAllByEmailContaining(String email);
  Long findOneMemberIdByEmail(String email);
}
