package com.starter.performance.controller;

import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.service.AdminMemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminMemberController {

    private final AdminMemberService adminMemberService;

    //회원 목록 조회
    //활성 계정(탈퇴 X)
    @GetMapping("/api/admin/members")
    public ResponseEntity<ResponseDto> memberList() {
        ResponseDto responseDto = adminMemberService.memberList();
        return ResponseEntity.ok(responseDto);
    }

    //회원 목록 조회
    //비활성 계정 포함
    @GetMapping("/api/admin/members/findAll")
    public ResponseEntity<ResponseDto> memberListAll() {
        ResponseDto responseDto = adminMemberService.memberListAll();
        return ResponseEntity.ok(responseDto);
    }

    //회원 검색
    @GetMapping("/api/admin/members/{email}")
    public ResponseEntity<ResponseDto> searchMember(@Valid @PathVariable String email) {
        ResponseDto responseDto = adminMemberService.searchMember(email);
        return ResponseEntity.ok(responseDto);
    }

    //회원 강제 탈퇴
    @DeleteMapping("api/admin/block/{email}")
    public ResponseEntity<ResponseDto> blockMember(@PathVariable String email) {
        ResponseDto responseDto = adminMemberService.blockMember(email);
        return ResponseEntity.ok(responseDto);
    }
}
