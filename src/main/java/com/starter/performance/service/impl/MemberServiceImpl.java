package com.starter.performance.service.impl;

import com.starter.performance.controller.dto.MemberProfileDto;
import com.starter.performance.domain.Member;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.service.MemberService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder encoder;

  ///@Autowired
  //private EntityManager entityManager;

  // 회원 정보 수정, 탈퇴할 때에도 비밀번호 확인과 동시에 토큰 확인 필요!
  // 회원 정보 수정 시 유효성 검사 필요!
  @Override
  public boolean confirmPassword(Authentication auth, String inputPassword) {
    Member authMember = (Member) auth.getPrincipal();
    String password = authMember.getPassword();
    return encoder.matches(inputPassword, password);
  }

  @Override
  @Transactional
  public void modifyProfile(MemberProfileDto memberProfileDto) {
    Optional<Member> optionalMember = memberRepository.findById(memberProfileDto.getMemberId());
    if (optionalMember.isPresent()) {
      Member member = Member.builder()
          .password(memberProfileDto.getPassword())
          .phoneNumber(memberProfileDto.getPhoneNumber())
          .nickname(memberProfileDto.getNickname())
          .build();
      memberRepository.save(member);
    }
  }

  @Override
  @Transactional
  public void withdrawalMember(Long id) {
    memberRepository.findById(id)
        .ifPresent(member -> memberRepository.deleteById(member.getMemberId()));
  }

  // entity 클래스의 filterDef, filter 어노테이션을 이용해서 soft delete 검색 포함, 비포함 설정
  // @where 은 무조건 비포함으로 포함 여부를 선택할 수 없음
  // 아래는 참고를 위해 만들어 놓은 메소드, 실제 구현과는 차이가 있을 수 있음

  /*
  public Iterable<Member> findAll(boolean isDeleted){
    Session session = entityManager.unwrap(Session.class);
    Filter filter = session.enableFilter("deletedProductFilter");
    filter.setParameter("isDeleted", isDeleted);
    Iterable<Member> members =  memberRepository.findAll();
    session.disableFilter("deletedProductFilter");
    return members;
  }
   */
}
