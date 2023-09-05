package com.starter.performance.repository;

import com.starter.performance.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByEmail(String email);

  @Query("SELECT m FROM Member m WHERE m.withdrawalDate = null")
  List<Member> findAllActiveMember();

  List<Member> findAllByEmailContaining(String email);

  Member findByNickname(String nickname);

  void deleteById(Long id);
}
