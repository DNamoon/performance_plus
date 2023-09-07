package com.starter.performance.controller.dto;

import com.starter.performance.domain.SnsType;

public interface SocialLoginInfoResponse {
    String getEmail();
    String getNickname();
    SnsType getSnsType();
}
