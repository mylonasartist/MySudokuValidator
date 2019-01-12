package com.mylonas.sta.sudoku.validator;

import com.mylonas.sta.sudoku.model.DataObject;
import com.mylonas.sta.sudoku.model.Grid;

import java.util.List;

public class RowConstraintValidator implements ConstraintValidator {

    @Override
    public void validate(Grid grid) throws ConstraintValidationException {
        List<List<DataObject>> rows = grid.getRows();
        int rowIndex = 1;
        for (List<DataObject> currentRow : rows) {
            try {
                ValidationHelper.validateDuplication(currentRow);
            }
            catch (ConstraintValidationException e) {
                throw new ConstraintValidationException("Invalid row " + rowIndex + ": " + e.getMessage(), e);
            }
            rowIndex++;
        }
    }
}
