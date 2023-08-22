package com.starter.performance.service.impl;

import com.starter.performance.domain.Member;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.service.AdminMemberService;
import com.starter.performance.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminMemberServiceImpl implements AdminMemberService {

  private final MemberRepository memberRepository;
  private final MemberService memberService;

  @Override
  public List<Member> memberList() {
    return memberRepository.findAll();
  }

  @Override
  @Transactional
  public List<Member> searchMember(String email) {
    return memberRepository.findAllByEmailContaining(email);
  }

  // 정확히 일치하는 email 을 입력받아야 함
  // SecurityConfig 이용해서 접근 설정
  @Override
  @Transactional
  public void blockMember(String email) {
    Long memberId = memberRepository.findOneMemberIdByEmail(email);

    if (memberId != null) {
      memberService.withdrawalMember(memberId);
      Member member = memberRepository.findById(memberId).orElse(null);
      if (member != null) {
        member.setSanctionWhether(true);
        memberRepository.save(member);
      }
    }
  }
}
