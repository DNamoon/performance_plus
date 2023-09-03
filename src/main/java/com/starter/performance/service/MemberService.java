package com.starter.performance.service;

import com.starter.performance.controller.dto.LoginRequestDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.controller.dto.SignUpRequestDto;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {

    ResponseDto signUp(SignUpRequestDto signUpRequestDto);

    ResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response);
}
