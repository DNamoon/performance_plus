package com.starter.performance.service;

import com.starter.performance.controller.dto.MemberProfileDto;
import org.springframework.security.core.Authentication;

public interface MemberService {

  // 회원 정보 수정, 탈퇴할 때에도 토큰 확인 필요!
  // 회원 정보 수정 시 유효성 검사 필요!
  boolean confirmPassword(Authentication auth, String inputPassword);
  void modifyProfile(MemberProfileDto memberProfileDto);
  void withdrawalMember(Long id);
}
