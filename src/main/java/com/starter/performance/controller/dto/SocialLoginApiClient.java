package com.starter.performance.controller.dto;

import com.starter.performance.domain.SnsType;

public interface SocialLoginApiClient {
    SnsType snsType();
    String requestAccessToken(SocialLoginParams params);
    SocialLoginInfoResponse requestSocialLoginInfo(String accessToken);
}
