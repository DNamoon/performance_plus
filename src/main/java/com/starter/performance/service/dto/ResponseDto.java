package com.starter.performance.service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseDto<T> {

    private final String statusCode;
    private final String message;
    private final T data;
}