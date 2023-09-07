package com.starter.performance.controller;

import com.starter.performance.controller.dto.AuthTokens;
import com.starter.performance.controller.dto.KakaoLoginParams;
import com.starter.performance.service.SocialLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class SocialLoginController {
    private final SocialLoginService socialLoginService;

    @PostMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao (@RequestBody KakaoLoginParams params) {
        return ResponseEntity.ok(socialLoginService.login(params));
    }
}
