package com.technicaltest.exception;

public class InvalidMovementException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidMovementException(String msg) {
        super(msg);
    }

    public InvalidMovementException(String msg, Exception ex) {
        super(msg, ex);
    }
}
