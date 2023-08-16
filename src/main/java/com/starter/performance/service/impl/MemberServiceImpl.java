package com.starter.performance.service.impl;

import com.starter.performance.controller.dto.MemberProfileDto;
import com.starter.performance.domain.Member;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.service.MemberService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;

  // 회원정보 조회, 수정, 탈퇴할 때에도 토큰 확인 필요!
  @Override
  public MemberProfileDto inquiryProfile(Long memberId) {
    Optional<Member> optionalMember = memberRepository.findById(memberId);
    if (!optionalMember.isPresent()) {
      return null;
    }
    return null;
  }

  @Override
  public void modifyProfile(MemberProfileDto memberProfileDto) {
    Member member = Member.builder()
        .memberId(memberProfileDto.getMemberId())
        .password(memberProfileDto.getPassword())
        .phoneNumber(memberProfileDto.getPhoneNumber())
        .nickname(memberProfileDto.getNickname())
        .modifiedDate(LocalDateTime.now())
        .build();
    memberRepository.save(member);
  }

  @Override
  public void withdrawalUser() {

  }

}
