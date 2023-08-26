package com.starter.performance.controller;

import com.starter.performance.controller.dto.LoginRequestDto;
import com.starter.performance.controller.dto.SignUpRequestDto;
import com.starter.performance.domain.Member;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.service.MemberService;
import com.starter.performance.service.dto.ResponseDto;
import java.security.Principal;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        return new ResponseEntity<>(
            new ResponseDto<>(
                "201",
                "회원가입 성공",
                memberService.signUp(signUpRequestDto)
            ),
            HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto,
        HttpServletResponse response) {
        return new ResponseEntity<>(
            new ResponseDto<>(
                "200",
                "로그인 성공",
                memberService.login(loginRequestDto, response)
            ),
            HttpStatus.OK
        );
    }

/*
    리뷰를 등록한 사용자의 이메일을 잘 받아오는지 확인하기 위한 임시 코드
    ++ 이메일 외에 다른 값도 잘 받아오는지 보기 위해 코드 추가함

    Authentication 객체에서 현재 로그인된 사용자의 이메일을 가져와서
    findByEmail 메서드를 통해 DB에서 해당 이메일을 가진 사용자 정보를 조회 후 리턴
*/

//    @PostMapping("/review")
//    public ResponseEntity<String> review(Authentication authentication) {
//        String email = authentication.getName();
//        Member member = memberRepository.findByEmail(email).get();
//
//        String password = member.getPassword();
//        String Nickname = member.getNickname();
//        String phoneNumber = member.getPhoneNumber();
//        String permission = member.getPermission().toString();
//
//        String memberInfo = "email: " + email + ", password: " + password + ", nickname: "
//            + Nickname + ", phoneNumber: " + phoneNumber + ", permission: " + permission;
//
//        return ResponseEntity.ok().body(memberInfo + "의 리뷰 등록 완료");
//    }
}