package com.starter.performance.service;

import com.starter.performance.controller.dto.MemberProfileDto;
import com.starter.performance.domain.Member;
import com.starter.performance.repository.MemberRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

@Slf4j
@SpringBootTest
class MemberServiceTest {
  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private MemberService memberService;

  // 실패
  @Test
  @WithMockUser(password = "test1")
  @DisplayName("비밀번호 확인 성공")
  void successConfirmPassword() {
    //given
    String inputPassword = "test1";
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    //when
    Boolean result = memberService.confirmPassword(auth, inputPassword);

    //then
    Assertions.assertThat(result).isTrue();
  }

  @Test
  @DisplayName("비밀번호 확인 실패")
  void failConfirmPassword() {

  }

  @Test
  @DisplayName("회원 정보 수정 성공")
  void successModifyProfileTest() {
    //given
    MemberProfileDto memberProfileDto = MemberProfileDto.builder()
        .memberId(1L)
        .password("password2")
        .phoneNumber("01011111111")
        .nickname("testnickname2")
        .build();

    //when
    memberService.modifyProfile(memberProfileDto);
    Optional<Member> id = memberRepository.findById(memberProfileDto.getMemberId());

    //then
    id.ifPresent(member -> Assertions.assertThat(member.getPassword())
        .isEqualTo(memberProfileDto.getPassword()));
    id.ifPresent(member -> Assertions.assertThat(member.getPhoneNumber())
        .isEqualTo(memberProfileDto.getPhoneNumber()));
    id.ifPresent(member -> Assertions.assertThat(member.getNickname())
        .isEqualTo(memberProfileDto.getNickname()));
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
  @DisplayName("회원 탈퇴 성공 - soft delete")
  void successWithdrawalMemberTest() {
    //given
    Member member = Member.builder()
        .memberId(1L)
        .build();

    //when
    memberService.withdrawalMember(member.getMemberId());
    Optional<Member> id = memberRepository.findById(member.getMemberId());

    //then
    id.ifPresent(value -> Assertions.assertThat(value.getWithdrawalDate()).isNotNull());
  }

  @Test
  @DisplayName("회원 탈퇴 실패 - 비밀번호 재확인 실패")
  void failWithdrawalMemberTest() {
    //given
    //when
    //then

  }
}