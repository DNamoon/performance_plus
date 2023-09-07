package com.starter.performance.controller.dto;

import com.starter.performance.domain.SnsType;
import org.springframework.util.MultiValueMap;

//OAuth 요청을 위한 파라미터 값
public interface SocialLoginParams {
    SnsType snstype();
    MultiValueMap<String, String> makeBody();
}
