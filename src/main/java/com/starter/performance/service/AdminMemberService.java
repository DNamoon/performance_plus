package com.starter.performance.service;

import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.domain.Member;
import java.util.List;

public interface AdminMemberService {

    ResponseDto memberList();

    ResponseDto memberListAll();

    ResponseDto searchMember(String email);

    ResponseDto blockMember(String email);
}
