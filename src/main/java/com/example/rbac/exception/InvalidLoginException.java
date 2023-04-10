package com.example.rbac.exception;

public class InvalidLoginException extends ServiceException {
    public InvalidLoginException(String message) {
        super(message);
    }
}
