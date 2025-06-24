package com.sangto.user_management.exceptions;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private Object[] args;

    public AppException(String message) {
        super(message);
    }

}
