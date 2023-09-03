package com.starter.performance.exception.impl;

import com.starter.performance.exception.AbstractException;
import com.starter.performance.exception.MemberErrorType;
import com.starter.performance.exception.dto.ErrorDataDto;
import org.springframework.http.HttpStatus;

public class WrongPasswordException extends AbstractException {

  @Override
  public int getStatusCode() {
    return HttpStatus.BAD_REQUEST.value();
  }

  @Override
  public ErrorDataDto getData() {
    return new ErrorDataDto(MemberErrorType.WRONG_PASSWORD_EXCEPTION.name());
  }
}
