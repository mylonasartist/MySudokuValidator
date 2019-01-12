package com.mylonas.sta.sudoku.validator;

import com.mylonas.sta.sudoku.model.Grid;
import org.junit.Assert;
import org.junit.Test;

public class ColumnConstraintValidatorTest {

    private static final ConstraintValidator columnConstraintValidator =
            new ColumnConstraintValidator();

    private static final Integer[][] validColumns = new Integer[][] {
            new Integer[] {1,1,1,1,1,1,1,1,1},
            new Integer[] {2,2,2,2,2,2,2,2,2},
            new Integer[] {3,3,3,3,3,3,3,3,3},
            new Integer[] {4,4,4,4,4,4,4,4,4},
            new Integer[] {5,5,5,5,5,5,5,5,5},
            new Integer[] {6,6,6,6,6,6,6,6,6},
            new Integer[] {7,7,7,7,7,7,7,7,7},
            new Integer[] {8,8,8,8,8,8,8,8,8},
            new Integer[] {9,9,9,9,9,9,9,9,9}
    };

    private static final Integer[][] invalidColumns = new Integer[][] {
            new Integer[] {1,1,1,1,1,1,1,1,1},
            new Integer[] {2,2,2,2,2,2,2,2,2},
            new Integer[] {3,3,3,3,3,3,3,3,3},
            new Integer[] {4,4,4,4,4,4,4,4,4},
            new Integer[] {5,5,5,5,5,5,5,5,5},
            new Integer[] {6,6,6,6,6,6,6,6,6},
            new Integer[] {7,7,7,7,7,7,3,7,7},
            new Integer[] {8,8,8,8,8,8,8,8,8},
            new Integer[] {9,9,9,9,9,9,9,9,9}
    };

    @Test
    public void validateValidColumns() {
        try {
            Grid grid = new Grid(validColumns);
            columnConstraintValidator.validate(grid);
        } catch (ConstraintValidationException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void validateInvalidColumns() {
        try {
            Grid grid = new Grid(invalidColumns);
            columnConstraintValidator.validate(grid);
            Assert.fail("No expected exception raised.");
        } catch (ConstraintValidationException e) {
            // can be ignored
        }
    }
}
