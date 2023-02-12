package com.epam.exception;

public class JwtAuthenticationException extends ServiceException{
    public JwtAuthenticationException(String s, Object... args) {
        super(s, args);
    }
}
