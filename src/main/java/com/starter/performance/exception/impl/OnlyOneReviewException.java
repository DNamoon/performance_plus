package com.starter.performance.exception.impl;

import com.starter.performance.exception.AbstractException;
import com.starter.performance.exception.ReviewErrorCode;
import com.starter.performance.exception.dto.ErrorDataDto;
import org.springframework.http.HttpStatus;

public class OnlyOneReviewException extends AbstractException {

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public Object getData() {
        return new ErrorDataDto(ReviewErrorCode.ONLY_ONE_REVIEW_PER_RESERVATION_EXCEPTION);
    }
}
