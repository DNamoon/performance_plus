package com.starter.performance.controller;

import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.domain.SuccessAdminMemberServiceType;
import com.starter.performance.service.AdminMemberService;
import com.starter.performance.service.dto.MemberInquiryResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminMemberController {
    private final AdminMemberService adminMemberService;

    //회원 목록 조회
    //활성 계정(탈퇴 X)
    @GetMapping("/members")
    public ResponseEntity<?> memberList() {
        List<MemberInquiryResponseDto> body = adminMemberService.memberList();
        return new ResponseEntity<>(
            new ResponseDto(
                HttpStatus.OK.value(),
                SuccessAdminMemberServiceType.SUCCESS_INQUIRY_MEMBER_MESSAGE.name(),
                body
            ),
            HttpStatus.OK
        );
    }

    //회원 목록 조회
    //비활성 계정 포함
    @GetMapping("/members/all")
    public ResponseEntity<?> memberListAll() {
        List<MemberInquiryResponseDto> body = adminMemberService.memberListAll();
        return new ResponseEntity<>(
            new ResponseDto(
                HttpStatus.OK.value(),
                SuccessAdminMemberServiceType.SUCCESS_INQUIRY_MEMBER_ALL_MESSAGE.name(),
                body
            ),
            HttpStatus.OK
        );
    }

    //회원 검색
    @GetMapping("/members/{email}")
    public ResponseEntity<ResponseDto> searchMember(@PathVariable String email) {
        List<MemberInquiryResponseDto> body = adminMemberService.searchMember(email);
        return new ResponseEntity<>(
            new ResponseDto(
                HttpStatus.OK.value(),
                SuccessAdminMemberServiceType.SUCCESS_FIND_MEMBER_ALL_MESSAGE.name(),
                body
            ),
            HttpStatus.OK
        );
    }

    //회원 강제 탈퇴
    @DeleteMapping("/member/{email}")
    public ResponseEntity<ResponseDto> blockMember(@PathVariable String email) {
        boolean body = adminMemberService.blockMember(email);
        return new ResponseEntity<>(
            new ResponseDto(
                HttpStatus.OK.value(),
                SuccessAdminMemberServiceType.SUCCESS_BLOCK_MEMBER_MESSAGE.name(),
                body
            ),
            HttpStatus.OK
        );
    }
}
