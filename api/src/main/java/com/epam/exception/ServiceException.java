package com.epam.exception;

public class ServiceException extends RuntimeException {
    private final Object[] args;

    public ServiceException(String s, Object... args) {
        super(s);
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }
}
