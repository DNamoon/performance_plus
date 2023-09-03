package com.starter.performance.service;

import com.starter.performance.controller.dto.MemberProfileRequestDto;
import com.starter.performance.controller.dto.ResponseDto;

public interface MemberService {

  // 회원 정보 수정, 탈퇴할 때에도 토큰 확인 필요!
  // 회원 정보 수정 시 유효성 검사 필요!
  ResponseDto confirmPassword(String email, String inputPassword);

  ResponseDto modifyProfile(MemberProfileRequestDto memberProfileRequestDto);

  ResponseDto withdrawalMember(Long id);
}
