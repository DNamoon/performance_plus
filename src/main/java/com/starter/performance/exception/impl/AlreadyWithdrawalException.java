package com.starter.performance.exception.impl;

import com.starter.performance.exception.AbstractException;
import com.starter.performance.exception.MemberProfileErrorType;
import com.starter.performance.exception.ErrorData;
import org.springframework.http.HttpStatus;

public class AlreadyWithdrawalException extends AbstractException {

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public ErrorData getData() {
        return new ErrorData(MemberProfileErrorType.ALREADY_WITHDRAWAL_EXCEPTION.name());
    }
}
