package com.example.rbac.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class ServiceException extends Throwable {
    @Getter
    private String message;
}
