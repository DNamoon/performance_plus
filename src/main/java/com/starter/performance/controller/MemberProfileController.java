package com.starter.performance.controller;

import com.starter.performance.controller.dto.MemberProfileRequestDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.service.MemberProfileService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberProfileController {

  private final MemberProfileService memberProfileService;

  //비밀번호 확인
  @PostMapping("/api/auth/confirmPassword")
  public ResponseEntity<ResponseDto> confirmPassword(Authentication auth, @Valid @RequestBody String inputPassword) {
    String email = auth.getName();
    ResponseDto responseDto = memberProfileService.confirmPassword(email, inputPassword);
    return ResponseEntity.ok(responseDto);
  }

  //회원 정보 수정
  @PatchMapping("/api/user/profile")
  public ResponseEntity<ResponseDto> modifyProfile(@Valid @RequestBody MemberProfileRequestDto memberProfileRequestDto) {
    ResponseDto responseDto = memberProfileService.modifyProfile(memberProfileRequestDto);
    return ResponseEntity.ok(responseDto);
  }

  //회원 탈퇴
  @DeleteMapping("/api/auth/signout/{id}")
  public ResponseEntity<ResponseDto> withdrawalMember(@PathVariable Long id) {
    ResponseDto responseDto = memberProfileService.withdrawalMember(id);
    return ResponseEntity.ok(responseDto);
  }
}
