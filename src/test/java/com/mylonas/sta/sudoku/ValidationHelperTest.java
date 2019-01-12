package com.mylonas.sta.sudoku;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationHelperTest {

    private static final List<Integer> noDuplicates = Arrays.asList(12, 4, 6, 89, 23, 17, 5, 3);
    private static final List<Integer> duplicates = Arrays.asList(12, 5, 4, 6, 89, 23, 17, 5, 3);

    private static final Integer[] validRows = new Integer[] {
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9
    };

    private static final Integer[] invalidRows = new Integer[] {
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,2,8,9,
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9,
            1,2,3,4,5,6,7,8,9
    };

    private static final Integer[] validColumns = new Integer[] {
            1,1,1,1,1,1,1,1,1,
            2,2,2,2,2,2,2,2,2,
            3,3,3,3,3,3,3,3,3,
            4,4,4,4,4,4,4,4,4,
            5,5,5,5,5,5,5,5,5,
            6,6,6,6,6,6,6,6,6,
            7,7,7,7,7,7,7,7,7,
            8,8,8,8,8,8,8,8,8,
            9,9,9,9,9,9,9,9,9
    };

    private static final Integer[] invalidColumns = new Integer[] {
            1,1,1,1,1,1,1,1,1,
            2,2,2,2,2,2,2,2,2,
            3,3,3,3,3,3,3,3,3,
            4,4,4,4,4,4,4,4,4,
            5,5,5,5,5,5,5,5,5,
            6,6,6,6,6,6,6,6,6,
            7,7,7,7,7,7,3,7,7,
            8,8,8,8,8,8,8,8,8,
            9,9,9,9,9,9,9,9,9
    };

    private static final Integer[] validBoxes = new Integer[] {
            1,2,3,1,2,3,1,2,3,
            4,5,6,4,5,6,4,5,6,
            7,8,9,7,8,9,7,8,9,
            1,2,3,1,2,3,1,2,3,
            4,5,6,4,5,6,4,5,6,
            7,8,9,7,8,9,7,8,9,
            1,2,3,1,2,3,1,2,3,
            4,5,6,4,5,6,4,5,6,
            7,8,9,7,8,9,7,8,9
    };

    private static final Integer[] invalidBoxes = new Integer[] {
            1,2,3,1,2,3,1,2,3,
            4,5,6,4,5,6,4,5,6,
            7,8,9,7,8,9,7,8,9,
            1,2,3,1,2,3,1,2,3,
            4,5,6,4,5,6,4,5,6,
            7,8,9,7,8,9,7,8,9,
            1,2,3,1,2,3,1,2,3,
            4,5,6,4,5,6,4,1,6,
            7,8,9,7,8,9,7,8,9
    };

    @Test
    public void validateNoDuplicates() {
        try {
            ValidationHelper.validateDuplication(noDuplicates.stream().map(value ->
                    new DataObject(value, 0)).collect(Collectors.toList()));
        } catch (ConstraintValidationException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void validateDuplicates() {
        try {
            ValidationHelper.validateDuplication(duplicates.stream().map(value ->
                    new DataObject(value, 0)).collect(Collectors.toList()));
            Assert.fail("No expected exception");
        } catch (ConstraintValidationException e) {
            // this can be ignored.
        }
    }

    @Test
    public void validateValidRows() {
        try {
            Grid grid = new Grid(validRows);
            ValidationHelper.validateRowConstraint(grid);
        } catch (ConstraintValidationException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void validateInvalidRows() {
        try {
            Grid grid = new Grid(invalidRows);
            ValidationHelper.validateRowConstraint(grid);
            Assert.fail("No expected exception raised.");
        } catch (ConstraintValidationException e) {
            // can be ignored
        }
    }

    @Test
    public void validateValidColumns() {
        try {
            Grid grid = new Grid(validColumns);
            ValidationHelper.validateColumnConstraint(grid);
        } catch (ConstraintValidationException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void validateInvalidColumns() {
        try {
            Grid grid = new Grid(invalidColumns);
            ValidationHelper.validateColumnConstraint(grid);
            Assert.fail("No expected exception raised.");
        } catch (ConstraintValidationException e) {
            // can be ignored
        }
    }

    @Test
    public void validateValidBoxes() {
        try {
            Grid grid = new Grid(validBoxes);
            ValidationHelper.validateBoxConstraint(grid);
        } catch (ConstraintValidationException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void validateInvalidBoxes() {
        try {
            Grid grid = new Grid(invalidBoxes);
            ValidationHelper.validateBoxConstraint(grid);
            Assert.fail("No expected exception raised.");
        } catch (ConstraintValidationException e) {
            // this can be ignored.
        }
    }
}
