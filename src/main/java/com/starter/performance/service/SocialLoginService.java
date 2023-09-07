package com.starter.performance.service;

import com.starter.performance.controller.dto.AuthTokens;
import com.starter.performance.controller.dto.SocialLoginInfoResponse;
import com.starter.performance.controller.dto.SocialLoginParams;
import com.starter.performance.domain.Member;
import com.starter.performance.domain.Permission;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.security.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocialLoginService {
    private final MemberRepository memberRepository;
    //토큰 가져와야 함!
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestSocialLoginInfoService requestSocialLoginInfoService;

    public AuthTokens login(SocialLoginParams params) {
        SocialLoginInfoResponse socialLoginInfoResponse = requestSocialLoginInfoService.request(params);
        Long memberId = findOrCreateMember(socialLoginInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(SocialLoginInfoResponse socialLoginInfoResponse) {
        return memberRepository.findByEmail(socialLoginInfoResponse.getEmail())
            .map(Member::getId)
            .orElseGet(() -> newMember(socialLoginInfoResponse));
    }

    private Long newMember(SocialLoginInfoResponse socialLoginInfoResponse) {
        Member member = Member.builder()
            .email(socialLoginInfoResponse.getEmail())
            .password("socialLogin")
            .phoneNumber("010-0000-0000")
            .nickname(socialLoginInfoResponse.getNickname())
            .permission(Permission.MEMBER)
            .snsType(socialLoginInfoResponse.getSnsType())
            .build();
        return memberRepository.save(member).getId();
    }
}
