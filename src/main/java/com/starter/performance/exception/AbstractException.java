package com.starter.performance.exception;

import com.starter.performance.exception.dto.ErrorDataDto;

public abstract class AbstractException extends RuntimeException {

  abstract public int getStatusCode();

  abstract public ErrorDataDto getData();
}
