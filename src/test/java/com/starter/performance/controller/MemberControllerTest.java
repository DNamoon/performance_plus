package com.starter.performance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.service.MemberProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MemberControllerTest.class)
class MemberControllerTest {
  @MockBean
  private MemberProfileController memberProfileController;

  @MockBean
  private MemberRepository memberRepository;

  @MockBean
  private MemberProfileService memberProfileService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private PasswordEncoder encoder;


}