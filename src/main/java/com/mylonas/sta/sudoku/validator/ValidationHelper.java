package com.mylonas.sta.sudoku.validator;

import com.mylonas.sta.sudoku.model.DataObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface ValidationHelper {
    public static void validateDuplication(List<DataObject> values) throws ConstraintValidationException {
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
}
