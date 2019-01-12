package com.mylonas.sta.sudoku;

class ConstraintValidationException extends Exception {

    ConstraintValidationException(String message) {
        super(message);
    }

    ConstraintValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
