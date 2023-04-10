package com.example.rbac.exception;

public class ExistingUserException extends ServiceException {
    public ExistingUserException(String message) {
        super(message);
    }
}
