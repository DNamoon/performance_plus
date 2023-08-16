package com.starter.performance.controller;

import com.starter.performance.controller.dto.MemberProfileDto;
import com.starter.performance.domain.Member;
import com.starter.performance.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class MemberController {
  private final MemberService memberService;
  private PasswordEncoder encoder;

  //비밀번호 확인
  @PostMapping("/api/auth/confirmPassword")
  public boolean confirmPassword(Authentication auth, @RequestBody String inputPassword) {
    Member member = (Member) auth.getPrincipal();
    String password = member.getPassword();
    if (encoder.matches(inputPassword, password)) {
      return true;
    } else {
      return false;
    }
  }

  @GetMapping("/api/user/profile/{email}")
  public MemberProfileDto inquiryProfile(@RequestParam Member member) {

  }

  //회원 정보 수정
  @PatchMapping("/api/user/profile/")
  public void modifyProfile(MemberProfileDto memberProfileDto) {
  memberService.modifyProfile(memberProfileDto);
  }

  //회원 탈퇴
  @PostMapping("/api/auth/signout/")
  public void signOutAccount(Member member) {

  }

}
