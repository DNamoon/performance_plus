package com.starter.performance.service.impl;

import com.starter.performance.controller.dto.MemberProfileRequestDto;
import com.starter.performance.domain.Member;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberProfileServiceImpl implements MemberProfileService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    // 회원 정보 수정, 탈퇴할 때에도 비밀번호 확인과 동시에 토큰 확인 필요!
    // 회원 정보 수정 시 유효성 검사 필요!
    @Override
    public boolean confirmPassword(String email, String inputPassword) {
        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isEmpty()) {
            throw new InvalidMemberException();
        }

        String password = member.get().getPassword();
        boolean matches = encoder.matches(inputPassword, password);

        if (!matches) {
            throw new WrongPasswordException();
        }

        return true;
    }

    @Override
    @Transactional
    public MemberProfileResponseDto modifyProfile(MemberProfileRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId())
            .orElseThrow(InvalidMemberException::new);

        checkMember(requestDto);

        String encodePassword = encoder.encode(requestDto.getPassword());
        member.setPassword(encodePassword);
        member.setPhoneNumber(requestDto.getPhoneNumber());
        member.setNickname(requestDto.getNickname());
        System.out.println("setMember");
        memberRepository.save(member);
        return MemberProfileResponseDto.builder()
            .email(member.getEmail())
            .phoneNumber(member.getPhoneNumber())
            .nickname(member.getNickname())
            .registeredDate(member.getRegisteredDate())
            .modifiedDate(member.getModifiedDate())
            .rating(member.getRating())
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
        if (memberRepository.findByNickname(memberProfileRequestDto.getNickname()).isPresent()) {
            throw new NicknameIsDuplicatedException();
        }
    }

    @Override
    @Transactional
    public boolean withdrawalMember(Long id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(InvalidMemberException::new);

        if (member.getWithdrawalDate() != null) {
            throw new AlreadyWithdrawalException();
        }

        memberRepository.delete(member);
        return true;
    }
}
