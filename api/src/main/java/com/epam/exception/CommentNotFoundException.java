package com.epam.exception;

public class CommentNotFoundException extends ServiceException {
    public CommentNotFoundException(String s, Object... args) {
        super(s, args);
    }
}
