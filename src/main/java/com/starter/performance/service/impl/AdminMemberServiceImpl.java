package com.starter.performance.service.impl;

import com.starter.performance.domain.Member;
import com.starter.performance.exception.impl.AlreadySanctionException;
import com.starter.performance.exception.impl.InvalidMemberException;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.service.AdminMemberService;
import com.starter.performance.service.dto.MemberInquiryResponseDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@RequiredArgsConstructor
@Service
public class AdminMemberServiceImpl implements AdminMemberService {
    private final MemberRepository memberRepository;

    @Override
    public List<MemberInquiryResponseDto> memberList() {
        List<Member> allActiveMember = memberRepository.findAllActiveMember();
        return getMemberInquiryResponseDto(allActiveMember);
    }

    @Override
    public List<MemberInquiryResponseDto> memberListAll() {
        List<Member> memberRepositoryAll = memberRepository.findAll();
        return getMemberInquiryResponseDto(memberRepositoryAll);
    }

    @Override
    @Transactional
    public List<MemberInquiryResponseDto> searchMember(String email) {
        List<Member> allByEmailContaining = memberRepository.findAllByEmailContaining(email);
        return getMemberInquiryResponseDto(allByEmailContaining);
    }

    private List<MemberInquiryResponseDto> getMemberInquiryResponseDto(
        List<Member> memberRepository) {
        if (CollectionUtils.isEmpty(memberRepository)) {
            throw new InvalidMemberException();
        }

        List<MemberInquiryResponseDto> responseDtoList = new ArrayList<>();
        for (Member member : memberRepository) {
            MemberInquiryResponseDto inquiryList = new MemberInquiryResponseDto(
                member.getId(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getNickname(),
                member.getRegisteredDate(),
                member.getWithdrawalDate(),
                member.getPermission().name(),
                member.isSanctionWhether(),
                member.getRating().getName().name()
            );
            responseDtoList.add(inquiryList);
        }
        return responseDtoList;
    }

    // 정확히 일치하는 email 을 입력받아야 함
    @Override
    @Transactional
    public boolean blockMember(String email) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(InvalidMemberException::new);

        if (member.getWithdrawalDate() != null && member.isSanctionWhether()) {
            throw new AlreadySanctionException();
        }

        member.setSanctionWhether(true);
        memberRepository.flush();
        memberRepository.delete(member);
        return true;
    }
}
