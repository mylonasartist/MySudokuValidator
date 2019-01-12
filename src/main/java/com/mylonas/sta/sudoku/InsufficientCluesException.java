package com.mylonas.sta.sudoku;

class InsufficientCluesException extends InvalidCluesDefinitionException {
    InsufficientCluesException(String message) {
        super(message);
    }
}
