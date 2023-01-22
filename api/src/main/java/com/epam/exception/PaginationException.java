package com.epam.exception;

public class PaginationException extends ServiceException {

    public PaginationException(String s, Object... args) {
        super(s, args);
    }
}
