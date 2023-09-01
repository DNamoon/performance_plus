package com.starter.performance.exception;

import com.starter.performance.exception.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(AbstractException.class)
    protected ResponseEntity<ErrorResponseDto> handleCustomException(AbstractException exception) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                .statusCode(exception.getStatusCode())
                .data((ErrorData) exception.getData())
                .build();

        return new ResponseEntity<>(errorResponseDto,
                Objects.requireNonNull(HttpStatus.resolve(exception.getStatusCode())));
    }
}

