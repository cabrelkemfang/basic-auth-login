package com.example.storemanagement.exception;

public final class ServiceError {
  private final int code;
  private final String message;

  private ServiceError(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public static ServiceError.Builder create(int code) {
    return new ServiceError.Builder(code);
  }

  public int getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.message;
  }

  public String toString() {
    return "ServiceError{code=" + this.code + ", message='" + this.message + '\'' + '}';
  }

  public static final class Builder {
    private final int code;
    private String message;

    public Builder(int code) {
      this.code = code;
    }

    public ServiceError.Builder message(String message) {
      this.message = message;
      return this;
    }

    public ServiceError build() {
      return new ServiceError(this.code, this.message);
    }
  }
}
