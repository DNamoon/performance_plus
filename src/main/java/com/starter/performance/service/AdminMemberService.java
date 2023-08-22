package com.starter.performance.service;

import com.starter.performance.domain.Member;
import java.util.List;

public interface AdminMemberService {

  List<Member> memberList();

  List<Member> searchMember(String email);

  void blockMember(String email);
}
