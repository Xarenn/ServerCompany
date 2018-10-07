package com.complex.server.service.security.Exceptions;

public class ExceptionsCode {

  public static final String JWTExpiredExceptionCode = "J001";
  public static final String UsernameTokenExcepction = "T001";
  public static final String TokenNotFound = "T002";
  private ExceptionsCode() {
    throw new IllegalStateException("Utility class");
  }

}
