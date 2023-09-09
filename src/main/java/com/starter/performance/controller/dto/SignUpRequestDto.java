package com.starter.performance.controller.dto;

import com.starter.performance.domain.Member;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SignUpRequestDto {

    @Pattern(regexp = "^[a-zA-Z0-9]+@[A-Za-z]+\\.[A-Za-z]+$",
        message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$",
        message = "비밀번호는 영문 대소문자와 숫자를 모두 사용하여 8~16자로 입력해야 합니다.")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9]{2,16}$",
        message = "닉네임은 영문 대소문자와 숫자를 사용하여 2~10자로 입력해야 합니다.")
    private String nickname;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",
        message = "전화번호 형식에 맞지 않습니다.")
    private String phoneNumber;

    public Member toEntity(BCryptPasswordEncoder bCryptPasswordEncoder) {
        return Member.builder()
            .email(email)
            .password(bCryptPasswordEncoder.encode(password))
            .nickname(nickname)
            .phoneNumber(phoneNumber)
            .build();
    }
}