package com.starter.performance.repository;

import com.starter.performance.domain.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

  @Query("SELECT m FROM Member m WHERE m.withdrawalDate = null")
  List<Member> findAllActiveMember();

  List<Member> findAllByEmailContaining(String email);

  Member findByNickname(String nickname);

  Member findByEmail(String email);
}
