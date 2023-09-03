package com.starter.performance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.performance.config.WebSecurityConfig;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MemberControllerTest.class)
class MemberControllerTest {
  @MockBean
  private MemberController memberController;

  @MockBean
  private MemberRepository memberRepository;

  @MockBean
  private MemberService memberService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private PasswordEncoder encoder;


}