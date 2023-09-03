package com.starter.performance.service;

import com.starter.performance.domain.Member;
import com.starter.performance.repository.MemberRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminMemberServiceTest {
  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private AdminMemberService adminMemberService;

  @Test
  @DisplayName("회원 목록 조회 성공")
  void successLoadMemberList() {
    //given

    //when

    //then

  }

  @Test
  @DisplayName("회원 목록 조회 실패 - 회원 없음")
  void failLoadMemberList() {
    //given
    //when
    //then
  }

  @Test
  @DisplayName("회원 검색 성공")
  void successSearchMemberList() {
    //given
    //when
    //then
  }

  @Test
  @DisplayName("회원 검색 실패 - 해당하는 회원 정보 없음")
  void failSearchMemberList() {
    //given
    //when
    //then
  }

  @Test
  @DisplayName("회원 강퇴 성공")
  void successBlockMemberList() {
    //given
    //when
    //then
  }

}