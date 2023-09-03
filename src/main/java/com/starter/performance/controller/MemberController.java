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
@RequestMapping("/api/auth")
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

    /*
        SecurityContextHolder에 담긴 인증 정보 제거

        하지만 토큰은 그대로 남아있기 때문에 토큰을 무효화 하지 않으면
        사용자는 만료되지 않은 토큰을 가지고 다시 인증에 시도할 수 있음

        --> 완벽한 로그아웃이라고 할 수 없음ㅠ
            우선 남은 기능들 먼저 구현하고 시간이 되면
            redis & RefreshToken을 이용해서 구현하는 방식으로 변경해야 할 듯
    */
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
