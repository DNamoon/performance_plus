package com.starter.performance.controller;

import com.starter.performance.domain.Member;
import com.starter.performance.service.AdminMemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class AdminMemberController {
  private final AdminMemberService adminMemberService;

  @GetMapping("/api/admin/memberlist")
  public List<Member> memberList() {
    return adminMemberService.memberList();
  }

  @GetMapping("/api/admin/memberlist/{email}")
  public List<Member> searchMember(@PathVariable String email) {
    return adminMemberService.searchMember(email);
  }

  @PatchMapping("api/admin/block/{email}")
  public void blockMember(@PathVariable String email) {
    adminMemberService.blockMember(email);
  }
}
