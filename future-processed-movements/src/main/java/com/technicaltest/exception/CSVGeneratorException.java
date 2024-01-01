package com.technicaltest.exception;

public class CSVGeneratorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CSVGeneratorException(String msg) {
        super(msg);
    }

    public CSVGeneratorException(String msg, Exception ex) {
        super(msg, ex);
    }
}
