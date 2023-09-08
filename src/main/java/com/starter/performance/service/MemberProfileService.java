package com.starter.performance.service;

import com.starter.performance.controller.dto.MemberProfileRequestDto;
import com.starter.performance.service.dto.MemberProfileResponseDto;

public interface MemberProfileService {

    // 회원 정보 수정, 탈퇴할 때에도 토큰 확인 필요!
    // 회원 정보 수정 시 유효성 검사 필요!
    boolean confirmPassword(String email, String inputPassword);

    MemberProfileResponseDto modifyProfile(MemberProfileRequestDto memberProfileRequestDto);

    boolean withdrawalMember(Long id);
}
