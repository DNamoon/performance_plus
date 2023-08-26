package com.starter.performance.exception.impl;

import com.starter.performance.exception.AbstractException;
import com.starter.performance.exception.ReviewErrorCode;
import com.starter.performance.exception.dto.ErrorDataDto;
import org.springframework.http.HttpStatus;

public class CanNotWriteReviewException extends AbstractException {

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public Object getData() {
        return new ErrorDataDto(ReviewErrorCode.CAN_NOT_WRITE_REVIEW_EXCEPTION.toString());
    }

}
