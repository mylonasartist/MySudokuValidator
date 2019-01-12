package com.mylonas.sta.sudoku.validator;

public class ConstraintValidationException extends Exception {

    public ConstraintValidationException(String message) {
        super(message);
    }

    public ConstraintValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
