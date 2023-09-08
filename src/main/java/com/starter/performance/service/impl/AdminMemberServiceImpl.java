package com.starter.performance.service.impl;

import com.starter.performance.domain.Member;
import com.starter.performance.exception.impl.AlreadySanctionException;
import com.starter.performance.exception.impl.InvalidMemberException;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.service.AdminMemberService;
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
    public List<Member> memberList() {
        List<Member> allActiveMember = memberRepository.findAllActiveMember();
        isEmptyList(allActiveMember);
        return allActiveMember;
    }

    @Override
    public List<Member> memberListAll() {
        List<Member> memberRepositoryAll = memberRepository.findAll();
        isEmptyList(memberRepositoryAll);
        return memberRepositoryAll;
    }

    @Override
    @Transactional
    public List<Member> searchMember(String email) {
        List<Member> allByEmailContaining = memberRepository.findAllByEmailContaining(email);
        isEmptyList(allByEmailContaining);
        return allByEmailContaining;
    }

    private void isEmptyList(List<Member> members) {
        if (CollectionUtils.isEmpty(members)) {
            throw new InvalidMemberException();
        }
    }

    // 정확히 일치하는 email 을 입력받아야 함
    @Override
    @Transactional
    public boolean blockMember(String email) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(InvalidMemberException::new);
        Long memberId = member.getId();

        if (member.getWithdrawalDate() != null && member.isSanctionWhether()) {
            throw new AlreadySanctionException();
        }

        member.setSanctionWhether(true);
        memberRepository.flush();
        memberRepository.deleteById(memberId);
        return true;
    }
}
