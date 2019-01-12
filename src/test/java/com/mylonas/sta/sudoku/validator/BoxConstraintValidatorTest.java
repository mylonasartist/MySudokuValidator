package com.mylonas.sta.sudoku.validator;

import com.mylonas.sta.sudoku.model.Grid;
import org.junit.Assert;
import org.junit.Test;

public class BoxConstraintValidatorTest {

    private static final ConstraintValidator boxConstraintValidator = new BoxConstraintValidator();

    private static final Integer[][] validBoxes = new Integer[][] {
            new Integer[] {1,2,3,1,2,3,1,2,3},
            new Integer[] {4,5,6,4,5,6,4,5,6},
            new Integer[] {7,8,9,7,8,9,7,8,9},
            new Integer[] {1,2,3,1,2,3,1,2,3},
            new Integer[] {4,5,6,4,5,6,4,5,6},
            new Integer[] {7,8,9,7,8,9,7,8,9},
            new Integer[] {1,2,3,1,2,3,1,2,3},
            new Integer[] {4,5,6,4,5,6,4,5,6},
            new Integer[] {7,8,9,7,8,9,7,8,9}
    };

    private static final Integer[][] invalidBoxes = new Integer[][] {
            new Integer[] {1,2,3,1,2,3,1,2,3},
            new Integer[] {4,5,6,4,5,6,4,5,6},
            new Integer[] {7,8,9,7,8,9,7,8,9},
            new Integer[] {1,2,3,1,2,3,1,2,3},
            new Integer[] {4,5,6,4,5,6,4,5,6},
            new Integer[] {7,8,9,7,8,9,7,8,9},
            new Integer[] {1,2,3,1,2,3,1,2,3},
            new Integer[] {4,5,6,4,5,6,4,1,6},
            new Integer[] {7,8,9,7,8,9,7,8,9}
    };

    @Test
    public void validateValidRows() {
        try {
            Grid grid = new Grid(validBoxes);
            boxConstraintValidator.validate(grid);
        } catch (ConstraintValidationException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void validateInvalidRows() {
        try {
            Grid grid = new Grid(invalidBoxes);
            boxConstraintValidator.validate(grid);
            Assert.fail("No expected exception raised.");
        } catch (ConstraintValidationException e) {
            // this can be ignored.
        }
    }
}
