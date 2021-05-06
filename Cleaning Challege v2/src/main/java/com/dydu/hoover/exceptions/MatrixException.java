package com.dydu.hoover.exceptions;

public class MatrixException extends Exception{

    public static final String MESSAGE_EXCEPTION = "NOT VALID MATRIX";

    public MatrixException(String message) {
        super(MESSAGE_EXCEPTION);
    }
}
