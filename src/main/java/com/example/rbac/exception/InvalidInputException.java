package com.example.rbac.exception;

public class InvalidInputException extends ServiceException {
    public InvalidInputException(String message) {
        super(message);
    }
}
