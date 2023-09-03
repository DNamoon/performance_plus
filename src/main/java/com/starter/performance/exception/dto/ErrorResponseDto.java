package com.starter.performance.exception.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponseDto {

  private int statusCode;
  private ErrorDataDto data;

  @Builder
  private ErrorResponseDto(int statusCode, ErrorDataDto data) {
    this.statusCode = statusCode;
    this.data = data;
  }
}
