package com.starter.performance.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDto {

    Integer statusCode;
    String message;
    Object data;
}
