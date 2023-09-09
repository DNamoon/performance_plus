package com.starter.performance.service.impl;

import com.starter.performance.controller.dto.LoginRequestDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.controller.dto.SignUpRequestDto;
import com.starter.performance.domain.Member;
import com.starter.performance.exception.impl.DuplicatedEmailException;
import com.starter.performance.exception.impl.DuplicatedNicknameException;
import com.starter.performance.exception.impl.UnsubscribedEmailException;
import com.starter.performance.exception.impl.WrongConfirmPasswordException;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.security.JwtUtil;
import com.starter.performance.service.MemberService;
import com.starter.performance.service.dto.LoginResponseDto;
import com.starter.performance.service.dto.SignUpResponseDto;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${jwt.secret.key}")
    private String secretKey;

    private Long expirationTime = 1000 * 60 * 60L;

    @Override
    @Transactional
    public ResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        memberRepository.findByEmail(signUpRequestDto.getEmail())
            .ifPresent(member -> {
                throw new DuplicatedEmailException();
            });

        memberRepository.findByNickname(signUpRequestDto.getNickname())
            .ifPresent(member -> {
                throw new DuplicatedNicknameException();
            });

        Member member = signUpRequestDto.toEntity(bCryptPasswordEncoder);
        memberRepository.save(member);

        return ResponseDto.builder()
            .statusCode(HttpStatus.CREATED.value())
            .message("회원가입 성공")
            .body(SignUpResponseDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build())
            .build();
    }

    @Override
    @Transactional
    public ResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        Member member = memberRepository.findByEmail(loginRequestDto.getEmail())
            .orElseThrow(() -> new UnsubscribedEmailException());

        if (!bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new WrongConfirmPasswordException();
        }

        String token = JwtUtil.createToken(
            member.getId(), member.getEmail(), member.getPermission().toString(),
            secretKey, expirationTime);

        response.addHeader("Authorization", "Bearer " + token);

        return ResponseDto.builder()
            .statusCode(HttpStatus.OK.value())
            .message("로그인 성공")
            .body(LoginResponseDto.builder()
                .email(member.getEmail())
                .build())
            .build();
    }
}
