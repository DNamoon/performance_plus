package com.starter.performance.exception.impl;

import com.starter.performance.exception.AbstractException;
import com.starter.performance.exception.MemberProfileErrorType;
import com.starter.performance.exception.ErrorData;
import org.springframework.http.HttpStatus;

public class InvalidMemberException extends AbstractException {

  @Override
  public int getStatusCode() {
    return HttpStatus.BAD_REQUEST.value();
  }

  @Override
  public ErrorData getData() {
    return new ErrorData(MemberProfileErrorType.INVALID_MEMBER_EXCEPTION.name());
  }
}
