package com.starter.performance.service;

import com.starter.performance.domain.Member;
import com.starter.performance.domain.Permission;
import com.starter.performance.domain.Rating;
import com.starter.performance.domain.RatingName;
import com.starter.performance.exception.impl.WrongConfirmPasswordException;
import com.starter.performance.repository.MemberRepository;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberProfileServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberProfileService memberProfileService;

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
            .permission(Permission.ROLE_MEMBER)
            .emailAuth(true)
            .sanctionWhether(false)
            .rating(new Rating(1))
            .build();
        memberRepository.save(member);

        //when
        String inputPassword = "1q2w3e4r";
        boolean result = memberProfileService.confirmPassword(member.getEmail(),
            inputPassword);

        //then
        Assertions.assertThat(result).isEqualTo(true);
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
            .permission(Permission.ROLE_MEMBER)
            .emailAuth(true)
            .sanctionWhether(false)
            .rating(new Rating(1))
            .build();
        memberRepository.save(member);

        //when //then
        String inputPassword = "wrongWord1";
        Assertions.assertThatThrownBy(() -> memberProfileService.confirmPassword(
            member.getEmail(), inputPassword))
            .isInstanceOf(WrongConfirmPasswordException.class);
    }

    @Test
    @DisplayName("회원 정보 수정 성공")
    void successModifyProfileTest() {
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

//    @Test
//    @Transactional
//    @DisplayName("회원 탈퇴 성공 - soft delete")
//    void successWithdrawalMemberTest() {
//        //given
//        Member member = Member.builder()
//            .id(10L)
//            .email("test10@gmail.com")
//            .password("password10")
//            .phoneNumber("010-0000-0000")
//            .nickname("nickname10")
//            .registeredDate(LocalDateTime.now())
//            .withdrawalDate(null)
//            .permission(Permission.MEMBER)
//            .emailAuth(true)
//            .sanctionWhether(false)
//            .rating(new Rating(1, "GENERAL"))
//            .build();
//        memberRepository.save(member);
//
//        //when
//        memberProfileService.withdrawalMember(member.getId());
//
//        //then
//        Assertions.assertThat(member.getWithdrawalDate()).isEqualTo(LocalDateTime.now());
//    }

    //탈퇴 일자가 null이 아닐 때 발생하는 예외
//    @Test
//    @Transactional
//    @DisplayName("회원 탈퇴 실패 - 이미 탈퇴한 회원인 경우")
//    void failWithdrawalMemberTest() {
//        //given
//        Member member = Member.builder()
//            .id(10L)
//            .email("test10@gmail.com")
//            .password("password10")
//            .phoneNumber("010-0000-0000")
//            .nickname("nickname10")
//            .registeredDate(LocalDateTime.now())
//            .withdrawalDate(LocalDateTime.now())
//            .permission(Permission.MEMBER)
//            .emailAuth(true)
//            .sanctionWhether(false)
//            .rating(new Rating(1, "GENERAL"))
//            .build();
//        memberRepository.save(member);
//
//        //when //then
//        Assertions.assertThatThrownBy(() -> memberProfileService.withdrawalMember(member.getId()))
//            .isInstanceOf(AlreadyWithdrawalException.class);
//    }
}