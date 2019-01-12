package com.mylonas.sta.sudoku.validator;

import com.mylonas.sta.sudoku.model.Grid;

public interface ConstraintValidator {

    void validate(Grid grid) throws ConstraintValidationException;
}
