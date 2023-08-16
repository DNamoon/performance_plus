package com.starter.performance.service;

import com.starter.performance.controller.dto.MemberProfileDto;
import com.starter.performance.domain.Member;

public interface MemberService {
  //회원정보수정/마이페이지조회/회원탈퇴
  MemberProfileDto inquiryProfile(Long id);
  void modifyProfile(MemberProfileDto memberProfileDto);
  void withdrawalUser();
}
