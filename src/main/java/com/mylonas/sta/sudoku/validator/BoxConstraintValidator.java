package com.mylonas.sta.sudoku.validator;

import com.mylonas.sta.sudoku.model.DataObject;
import com.mylonas.sta.sudoku.model.Grid;

import java.util.List;

public class BoxConstraintValidator implements ConstraintValidator {
    @Override
    public void validate(Grid grid) throws ConstraintValidationException {
        List<List<DataObject>> boxes = grid.getBoxes();
        int boxIndex = 1;
        for (List<DataObject> currentBox : boxes) {
            try {
                ValidationHelper.validateDuplication(currentBox);
            }
            catch (ConstraintValidationException e) {
                throw new ConstraintValidationException("Invalid box " + boxIndex + ": " + e.getMessage(), e);
            }
            boxIndex++;
        }
    }
}
