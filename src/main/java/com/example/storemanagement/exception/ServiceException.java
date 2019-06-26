package com.example.storemanagement.exception;

import java.text.MessageFormat;

public final class ServiceException extends RuntimeException {
  private final ServiceError serviceError;

  public ServiceException(ServiceError serviceError) {
    super(serviceError.getMessage());
    this.serviceError = serviceError;
  }

  public static ServiceException badRequest(String message, Object... args) {
    return new ServiceException(ServiceError.create(400).message(MessageFormat.format(message, args)).build());
  }

  public static ServiceException notFound(String message, Object... args) {
    return new ServiceException(ServiceError.create(404).message(MessageFormat.format(message, args)).build());
  }

  public static ServiceException conflict(String message, Object... args) {
    return new ServiceException(ServiceError.create(409).message(MessageFormat.format(message, args)).build());
  }

  public static ServiceException internalError(String message, Object... args) {
    return new ServiceException(ServiceError.create(500).message(MessageFormat.format(message, args)).build());
  }

  public ServiceError serviceError() {
    return this.serviceError;
  }

  public String toString() {
    return "ServiceException{serviceError=" + this.serviceError + '}';
  }
}

