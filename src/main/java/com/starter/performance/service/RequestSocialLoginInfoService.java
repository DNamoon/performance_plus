package com.starter.performance.service;

import com.starter.performance.controller.dto.SocialLoginApiClient;
import com.starter.performance.controller.dto.SocialLoginInfoResponse;
import com.starter.performance.controller.dto.SocialLoginParams;
import com.starter.performance.domain.SnsType;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RequestSocialLoginInfoService {

    private final Map<SnsType, SocialLoginApiClient> clients;

    public RequestSocialLoginInfoService(List<SocialLoginApiClient> clients) {
        this.clients = clients.stream().collect(
            Collectors.toUnmodifiableMap(SocialLoginApiClient::snsType, Function.identity()));
    }

    public SocialLoginInfoResponse request(SocialLoginParams params) {
        SocialLoginApiClient client = clients.get(params.snstype());
        String accessToken = client.requestAccessToken(params);
        return client.requestSocialLoginInfo(accessToken);
    }

}
