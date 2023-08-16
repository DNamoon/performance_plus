package com.starter.performance.service;

import com.starter.performance.controller.dto.MemberProfileDto;
import com.starter.performance.domain.Member;

public interface MemberService {
  MemberProfileDto inquiryProfile(Long id);
  void modifyProfile(MemberProfileDto memberProfileDto);
  void withdrawalMember(Long id);
}
