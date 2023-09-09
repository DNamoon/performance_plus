package com.starter.performance.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.performance.exception.AuthErrorCode;
import com.starter.performance.exception.ErrorData;
import com.starter.performance.exception.dto.ErrorResponseDto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        jsonErrorResponse(response, HttpStatus.UNAUTHORIZED,
            AuthErrorCode.AUTHENTICATION_IS_FAILED_EXCEPTION.name());
    }

    private void jsonErrorResponse(HttpServletResponse response, HttpStatus httpStatus,
        String errorCode) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType("application/json; charset=UTF-8");

        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
            .statusCode(httpStatus.value())
            .data(new ErrorData(errorCode))
            .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(errorResponseDto);
        response.getWriter().write(jsonResponse);
    }
}
