package com.starter.performance.controller;

import com.starter.performance.controller.dto.MemberProfileDto;
import com.starter.performance.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class MemberController {

  private final MemberService memberService;

  //비밀번호 확인
  @PostMapping("/api/auth/confirmPassword")
  public boolean confirmPassword(Authentication auth, @RequestBody String inputPassword ) {
    return memberService.confirmPassword(auth, inputPassword);
  }

  //회원 정보 수정
  @PatchMapping("/api/user/profile/")
  public void modifyProfile(@RequestBody MemberProfileDto memberProfileDto) {
    memberService.modifyProfile(memberProfileDto);
  }

  //회원 탈퇴
  @PostMapping("/api/auth/signout/")
  public void withdrawalMember(@RequestBody Long id) {
    memberService.withdrawalMember(id);
  }

}
