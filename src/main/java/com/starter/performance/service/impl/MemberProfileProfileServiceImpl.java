package com.starter.performance.service.impl;

import com.starter.performance.controller.dto.MemberProfileRequestDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.domain.Member;
import com.starter.performance.domain.SuccessMemberProfileServiceType;
import com.starter.performance.exception.impl.AlreadyWithdrawalException;
import com.starter.performance.exception.impl.InvalidMemberException;
import com.starter.performance.exception.impl.InvalidNicknameException;
import com.starter.performance.exception.impl.InvalidPasswordException;
import com.starter.performance.exception.impl.InvalidPhoneNumberException;
import com.starter.performance.exception.impl.NicknameIsDuplicatedException;
import com.starter.performance.exception.impl.WrongPasswordException;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.service.MemberProfileService;
import com.starter.performance.service.dto.MemberProfileResponseDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberProfileProfileServiceImpl implements MemberProfileService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    // 회원 정보 수정, 탈퇴할 때에도 비밀번호 확인과 동시에 토큰 확인 필요!
    // 회원 정보 수정 시 유효성 검사 필요!
    @Override
    public ResponseDto confirmPassword(String email, String inputPassword) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isEmpty()) {
            throw new InvalidMemberException();
        }
        String password = member.get().getPassword();
        boolean matches = encoder.matches(inputPassword, password);
        if (!matches) {
            throw new WrongPasswordException();
        }
        return ResponseDto.builder()
            .message(SuccessMemberProfileServiceType.SUCCESS_CONFIRM_PASSWORD_MESSAGE.name())
            .statusCode(HttpStatus.OK.value())
            .body(true)
            .build();
    }

    @Override
    @Transactional
    public ResponseDto modifyProfile(MemberProfileRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId())
            .orElseThrow(InvalidMemberException::new);

        checkMember(requestDto);

        String encodePassword = encoder.encode(requestDto.getPassword());
        member.setPassword(encodePassword);
        member.setPhoneNumber(requestDto.getPhoneNumber());
        member.setNickname(requestDto.getNickname());
        memberRepository.save(member);
        return ResponseDto.builder()
            .message(SuccessMemberProfileServiceType.SUCCESS_MODIFY_PROFILE_MESSAGE.name())
            .statusCode(HttpStatus.OK.value())
            .body(MemberProfileResponseDto.builder()
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .nickname(member.getNickname())
                .registeredDate(member.getRegisteredDate())
                .modifiedDate(member.getModifiedDate())
                .rating(member.getRating())
                .build())
            .build();
    }

    private void checkMember(MemberProfileRequestDto memberProfileRequestDto) {
        if (!memberProfileRequestDto.getPassword()
            .matches("^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$")) {
            throw new InvalidPasswordException();
        }
        if (!memberProfileRequestDto.getPhoneNumber()
            .matches("^\\d{2,3}-\\d{3,4}-\\d{4}$")) {
            throw new InvalidPhoneNumberException();
        }
        if (!memberProfileRequestDto.getNickname()
            .matches("^[a-zA-Z0-9]{2,10}$")) {
            throw new InvalidNicknameException();
        }
        if (memberRepository.findByNickname(memberProfileRequestDto.getNickname()) != null) {
            throw new NicknameIsDuplicatedException();
        }
    }

    @Override
    @Transactional
    public ResponseDto withdrawalMember(Long id) {
        isWithdrawalMember(id);
        Member member = memberRepository.findById(id).orElse(null);
        memberRepository.delete(member);
        return ResponseDto.builder()
            .message(SuccessMemberProfileServiceType.SUCCESS_WITHDRAWAL_MEMBER_MESSAGE.name())
            .statusCode(HttpStatus.OK.value())
            .body(null)
            .build();
    }

    private void isWithdrawalMember(Long id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(InvalidMemberException::new);
        if (member.getWithdrawalDate() != null) {
            throw new AlreadyWithdrawalException();
        }
    }
}
