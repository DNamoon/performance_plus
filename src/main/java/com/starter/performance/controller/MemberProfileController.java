package com.starter.performance.controller;

import com.starter.performance.controller.dto.MemberProfileRequestDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.domain.SuccessMemberProfileServiceType;
import com.starter.performance.service.MemberProfileService;
import com.starter.performance.service.dto.ConfirmPasswordRequestDto;
import com.starter.performance.service.dto.MemberProfileResponseDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @PostMapping("/auth/password")
    public ResponseEntity<?> confirmPassword(Authentication auth,
        @RequestBody ConfirmPasswordRequestDto input) {
        String email = auth.getName();
        String inputPassword = input.getInputPassword();
        boolean body = memberProfileService.confirmPassword(email, inputPassword);
        return new ResponseEntity<>(
            new ResponseDto(
                HttpStatus.OK.value(),
                SuccessMemberProfileServiceType.SUCCESS_CONFIRM_PASSWORD_MESSAGE.name(),
                body
            ),
            HttpStatus.OK
        );
    }

    //회원 정보 수정
    @PatchMapping("/member/profile")
    public ResponseEntity<?> modifyProfile(
        @Valid @RequestBody MemberProfileRequestDto requestDto) {
        MemberProfileResponseDto body = memberProfileService.modifyProfile(requestDto);
        return new ResponseEntity<>(
            new ResponseDto(
                HttpStatus.OK.value(),
                SuccessMemberProfileServiceType.SUCCESS_MODIFY_PROFILE_MESSAGE.name(),
                body
            ),
            HttpStatus.OK
        );
    }

    //회원 탈퇴
    @DeleteMapping("/member/{memberId}")
    public ResponseEntity<?> withdrawalMember(@PathVariable Long memberId) {
        boolean body = memberProfileService.withdrawalMember(memberId);
        return new ResponseEntity<>(
            new ResponseDto(
                HttpStatus.OK.value(),
                SuccessMemberProfileServiceType.SUCCESS_WITHDRAWAL_MEMBER_MESSAGE.name(),
                body
            ),
            HttpStatus.OK
        );
    }
}
