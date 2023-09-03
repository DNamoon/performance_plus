package com.starter.performance.exception;

import lombok.Getter;

@Getter
public enum AuthErrorCode {
    EMAIL_IS_DUPLICATED_EXCEPTION,
    NICKNAME_IS_DUPLICATED_EXCEPTION,
    UNSUBSCRIBED_EMAIL_EXCEPTION,
    WRONG_PASSWORD_EXCEPTION
}
