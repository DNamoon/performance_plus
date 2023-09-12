package com.starter.performance.service;

import com.starter.performance.service.dto.MemberInquiryResponseDto;
import java.util.List;

public interface AdminMemberService {

    List<MemberInquiryResponseDto> memberList();

    List<MemberInquiryResponseDto> memberListAll();

    List<MemberInquiryResponseDto> searchMember(String email);

    boolean blockMember(String email);
}
