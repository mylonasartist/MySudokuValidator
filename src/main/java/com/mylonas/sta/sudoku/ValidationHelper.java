package com.mylonas.sta.sudoku;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

interface ValidationHelper {

    static void validateDuplication(List<DataObject> values) throws ConstraintValidationException {
        Set<DataObject> valuesSet = new HashSet<>(values.size());
        for (DataObject currentValue : values) {
            if (currentValue != null) {
                if (valuesSet.contains(currentValue)) {
                    throw new ConstraintValidationException("Value " + currentValue + " is duplicated.");
                }
                valuesSet.add(currentValue);
            }
        }
    }

    static void validateRowConstraint(Grid grid) throws ConstraintValidationException {
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

    static void validateColumnConstraint(Grid grid) throws ConstraintValidationException {
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

    static void validateBoxConstraint(Grid grid) throws ConstraintValidationException {
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
