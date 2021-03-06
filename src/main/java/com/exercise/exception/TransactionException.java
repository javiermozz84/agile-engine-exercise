package com.exercise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TransactionException(final String message) {
        super(message);
    }

    public TransactionException(final String message, Throwable cause) { super(message, cause); }
}
