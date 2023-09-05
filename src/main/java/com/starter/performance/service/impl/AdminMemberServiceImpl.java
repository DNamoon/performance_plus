package com.starter.performance.service.impl;

import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.domain.Member;
import com.starter.performance.domain.SuccessAdminMemberServiceType;
import com.starter.performance.exception.impl.AlreadySanctionException;
import com.starter.performance.exception.impl.InvalidMemberException;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.service.AdminMemberService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@RequiredArgsConstructor
@Service
public class AdminMemberServiceImpl implements AdminMemberService {

  private final MemberRepository memberRepository;

  @Override
  public ResponseDto memberList() {
    List<Member> allActiveMember = memberRepository.findAllActiveMember();
    isEmptyList(allActiveMember);
    return ResponseDto.builder()
        .message(SuccessAdminMemberServiceType.SUCCESS_INQUIRY_MEMBER_MESSAGE.name())
        .statusCode(HttpStatus.OK.value())
        .body(allActiveMember)
        .build();
  }

  @Override
  public ResponseDto memberListAll() {
    List<Member> memberRepositoryAll = memberRepository.findAll();
    isEmptyList(memberRepositoryAll);
    return ResponseDto.builder()
        .message(SuccessAdminMemberServiceType.SUCCESS_INQUIRY_MEMBER_ALL_MESSAGE.name())
        .statusCode(HttpStatus.OK.value())
        .body(memberRepositoryAll)
        .build();
  }

  @Override
  @Transactional
  public ResponseDto searchMember(String email) {
    List<Member> allByEmailContaining = memberRepository.findAllByEmailContaining(email);
    isEmptyList(allByEmailContaining);
    return ResponseDto.builder()
        .message(SuccessAdminMemberServiceType.SUCCESS_FIND_MEMBER_ALL_MESSAGE.name())
        .statusCode(HttpStatus.OK.value())
        .body(allByEmailContaining)
        .build();
  }

  private void isEmptyList(List<Member> members) {
    if (CollectionUtils.isEmpty(members)) {
      throw new InvalidMemberException();
    }
  }

  // 정확히 일치하는 email 을 입력받아야 함
  @Override
  @Transactional
  public ResponseDto blockMember(String email) {
    Optional<Member> member = memberRepository.findByEmail(email);
    Long memberId = member.orElseThrow(InvalidMemberException::new).getId();
    isBlockMember(memberId);
    member.orElseThrow().setSanctionWhether(true);
    memberRepository.flush();
    memberRepository.deleteById(memberId);
    return ResponseDto.builder()
        .message(SuccessAdminMemberServiceType.SUCCESS_BLOCK_MEMBER_MESSAGE.name())
        .statusCode(HttpStatus.OK.value())
        .body(null)
        .build();
  }

  private void isBlockMember(Long id) {
    Member member = memberRepository.findById(id)
        .orElseThrow(InvalidMemberException::new);
    if (member.getWithdrawalDate() != null && member.isSanctionWhether()) {
      throw new AlreadySanctionException();
    }
  }
}
