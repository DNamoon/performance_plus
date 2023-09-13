package com.starter.performance.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.performance.exception.ErrorData;
import com.starter.performance.exception.JwtErrorCode;
import com.starter.performance.exception.dto.ErrorResponseDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@NoArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException exception) {
            log.error("만료된 토큰");
            jsonErrorResponse(response, HttpStatus.UNAUTHORIZED,
                JwtErrorCode.EXPIRED_TOKEN.name());
        } catch (MalformedJwtException exception) {
            log.error("유효하지 않은 토큰");
            jsonErrorResponse(response, HttpStatus.UNAUTHORIZED,
                JwtErrorCode.INVALID_TOKEN.name());
        } catch (SignatureException exception) {
            log.error("유효하지 않은 서명");
            jsonErrorResponse(response, HttpStatus.UNAUTHORIZED,
                JwtErrorCode.INVALID_TOKEN_SIGNATURE.name());
        } catch (UnsupportedJwtException exception) {
            log.error("지원하지 않는 토큰");
            jsonErrorResponse(response, HttpStatus.UNAUTHORIZED,
                JwtErrorCode.UNSUPPORTED_TOKEN.name());
        }
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