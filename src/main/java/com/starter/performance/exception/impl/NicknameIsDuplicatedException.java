package com.starter.performance.exception.impl;

import com.starter.performance.exception.AbstractException;
import com.starter.performance.exception.MemberErrorType;
import com.starter.performance.exception.dto.ErrorDataDto;
import org.springframework.http.HttpStatus;

public class NicknameIsDuplicatedException extends AbstractException {

  @Override
  public int getStatusCode() {
    return HttpStatus.BAD_REQUEST.value();
  }

  @Override
  public ErrorDataDto getData() {
    return new ErrorDataDto(MemberErrorType.NICKNAME_IS_DUPLICATED_EXCEPTION.name());
  }
}
