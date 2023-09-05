package com.starter.performance.exception.impl;

import com.starter.performance.exception.AbstractException;
import com.starter.performance.exception.MemberErrorType;
import com.starter.performance.exception.ErrorData;
import org.springframework.http.HttpStatus;

public class NicknameIsDuplicatedException extends AbstractException {

  @Override
  public int getStatusCode() {
    return HttpStatus.BAD_REQUEST.value();
  }

  @Override
  public ErrorData getData() {
    return new ErrorData(MemberErrorType.NICKNAME_IS_DUPLICATED_EXCEPTION.name());
  }
}
