package com.mylonas.sta.sudoku.validator;

import com.mylonas.sta.sudoku.model.Grid;
import org.junit.Assert;
import org.junit.Test;

public class RowConstraintValidatorTest {

    private static final ConstraintValidator rowConstraintValidator = new RowConstraintValidator();

    private static final Integer[][] validRows = new Integer[][] {
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9}
    };

    private static final Integer[][] invalidRows = new Integer[][] {
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,2,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9}
    };

    @Test
    public void validateValidRows() {
        try {
            Grid grid = new Grid(validRows);
            rowConstraintValidator.validate(grid);
        } catch (ConstraintValidationException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void validateInvalidRows() {
        try {
            Grid grid = new Grid(invalidRows);
            rowConstraintValidator.validate(grid);
            Assert.fail("No expected exception raised.");
        } catch (ConstraintValidationException e) {
            // can be ignored
        }
    }
}
