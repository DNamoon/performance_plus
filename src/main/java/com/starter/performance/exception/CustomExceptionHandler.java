package com.starter.performance.exception;

import static com.starter.performance.exception.ErrorType.INVALID_REQUEST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.starter.performance.exception.dto.ErrorResponseDto;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AbstractException.class)
    protected ResponseEntity<ErrorResponseDto> handleCustomException(AbstractException exception) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
            .statusCode(exception.getStatusCode())
            .data(exception.getData())
            .build();

        return new ResponseEntity<>(errorResponseDto,
            Objects.requireNonNull(HttpStatus.resolve(exception.getStatusCode())));
    }


    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponseDto> bindException(BindException exception) {
        return ResponseEntity.badRequest()
            .body(ErrorResponseDto.builder()
                .statusCode(BAD_REQUEST.value())
                .data(new ErrorData(INVALID_REQUEST.name()))
                .build()
            );

    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> illegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest()
            .body(ErrorResponseDto.builder()
                .statusCode(BAD_REQUEST.value())
                .data(new ErrorData(INVALID_REQUEST.name()))
                .build()
            );
    }
}

