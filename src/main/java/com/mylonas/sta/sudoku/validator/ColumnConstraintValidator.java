package com.mylonas.sta.sudoku.validator;

import com.mylonas.sta.sudoku.model.DataObject;
import com.mylonas.sta.sudoku.model.Grid;

import java.util.List;

public class ColumnConstraintValidator implements ConstraintValidator {

    @Override
    public void validate(Grid grid) throws ConstraintValidationException {
        List<List<DataObject>> columns = grid.getColumns();
        int columnIndex = 1;
        for (List<DataObject> currentColumn : columns) {
            try {
                ValidationHelper.validateDuplication(currentColumn);
            }
            catch (ConstraintValidationException e) {
                throw new ConstraintValidationException("Invalid column " + columnIndex + ": " + e.getMessage(), e);
            }
            columnIndex++;
        }
    }
}
