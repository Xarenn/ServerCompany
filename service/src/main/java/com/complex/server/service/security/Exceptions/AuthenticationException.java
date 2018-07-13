package com.complex.server.service.security.Exceptions;

public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = -3261440999692965330L;

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
