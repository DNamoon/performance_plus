package com.starter.performance.controller;

import com.starter.performance.controller.dto.LoginRequestDto;
import com.starter.performance.controller.dto.SignUpRequestDto;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.service.MemberService;
import com.starter.performance.controller.dto.ResponseDto;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        ResponseDto responseDto = memberService.signUp(signUpRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto,
        HttpServletResponse response) {
        ResponseDto responseDto = memberService.login(loginRequestDto, response);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return ResponseEntity.ok().body("인증 정보 제거 성공");
        } else {
            return ResponseEntity.ok().body("인증 정보 제거 실패");
        }
    }
}
