package com.starter.performance.service;

import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.domain.Member;
import com.starter.performance.domain.Permission;
import com.starter.performance.domain.Rating;
import com.starter.performance.exception.impl.WrongPasswordException;
import com.starter.performance.repository.MemberRepository;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
class MemberServiceTest {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private MemberService memberService;

  // 실패
  @Test
  @DisplayName("비밀번호 확인 성공")
  @Transactional
  void successConfirmPassword() {
    //given
    Member member = Member.builder()
        .id(10L)
        .email("test10@gmail.com")
        .password("$2a$10$6CBzPdU6/gFNMqNo.sqZcOCINafq6JQuZfyDeB/V787euOlpGGkSu")
        .phoneNumber("010-0000-0000")
        .nickname("nickname10")
        .registeredDate(LocalDateTime.now())
        .permission(Permission.MEMBER.name())
        .emailAuth(true)
        .sanctionWhether(false)
        .rating(new Rating(1, "GENERAL"))
        .build();
    memberRepository.save(member);

    //when
    String inputPassword = "1q2w3e4r";
    ResponseDto responseDto = memberService.confirmPassword(member.getEmail(), inputPassword);

    //then
    Assertions.assertThat(responseDto.getBody()).isEqualTo(true);
  }

  @Test
  @DisplayName("비밀번호 확인 실패")
  @Transactional
  void failConfirmPassword() {
    //given
    Member member = Member.builder()
        .id(10L)
        .email("test10@gmail.com")
        .password("$2a$10$6CBzPdU6/gFNMqNo.sqZcOCINafq6JQuZfyDeB/V787euOlpGGkSu")
        .phoneNumber("010-0000-0000")
        .nickname("nickname10")
        .registeredDate(LocalDateTime.now())
        .permission(Permission.MEMBER.name())
        .emailAuth(true)
        .sanctionWhether(false)
        .rating(new Rating(1, "GENERAL"))
        .build();
    memberRepository.save(member);

    //when
    String inputPassword = "wrongWord1";
    AbstractThrowableAssert<?, ? extends Throwable> responseDto =
    //then
    Assertions.assertThatThrownBy(() -> memberService.confirmPassword(member.getEmail(), inputPassword))
        .isInstanceOf(WrongPasswordException.class);
  }

  @Test
  @DisplayName("회원 정보 수정 성공")
  void successModifyProfileTest() {
    //given

    //when

    //then
  }

  @Test
  @DisplayName("회원 정보 수정 실패 - 비밀번호 재확인 실패")
  void failConfirmPasswordModifyProfileTest() {
    //given
    //when
    //then
  }

  @Test
  @DisplayName("회원 정보 수정 실패 - 데이터 유효성 검사 실패")
  void failInvalidDataModifyProfileTest() {
    //given
    //when
    //then
  }

  @Test
  @Transactional
  @DisplayName("회원 탈퇴 성공 - soft delete")
  void successWithdrawalMemberTest() {
    //given

    //when

    //then

  }

  @Test
  @DisplayName("회원 탈퇴 실패 - 비밀번호 재확인 실패")
  void failWithdrawalMemberTest() {
    //given
    //when
    //then

  }
}