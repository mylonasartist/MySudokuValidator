package com.mylonas.sta.sudoku;

import org.junit.Assert;
import org.junit.Test;

public class GridTest {
    private static final Integer[] validGrid = new Integer[] {
            5,3,null,null,7,null,null,null,null,
            6,null,null,1,9,5,null,null,null,
            null,9,8,null,null,null,null,6,null,
            8,null,null,null,6,null,null,null,3,
            4,null,null,8,null,3,null,null,1,
            7,null,null,null,2,null,null,null,6,
            null,6,null,null,null,null,2,8,null,
            null,null,null,4,1,9,null,null,5,
            null,null,null,null,8,null,null,7,9
    };

    private static final Integer[] anotherValidGrid = new Integer[] {
            9,null,4,null,6,null,7,null,1,
            null,2,null,4,null,3,null,8,null,
            8,null,null,null,null,null,null,null,4,
            null,null,1,8,4,9,6,null,null,
            null,null,null,null,null,null,null,null,null,
            null,null,3,2,5,7,9,null,null,
            4,null,null,null,null,null,null,null,7,
            null,8,null,6,null,4,null,5,null,
            5,null,6,null,8,null,2,null,3
    };

    private static final Integer[] insolubleGrid = new Integer[] {
            5,1,6,8,4,9,7,3,2,
            3,null,7,6,null,5,null,null,null,
            8,null,9,7,null,null,null,6,5,
            1,3,5,null,6,null,9,null,7,
            4,7,2,5,9,1,null,null,6,
            9,6,8,3,7,null,null,5,null,
            2,5,3,1,8,6,null,7,4,
            6,8,4,2,null,7,5,null,null,
            7,9,1,null,5,null,6,null,8,
    };

    @Test
    public void validateValidGrid() {
        Grid grid = new Grid(validGrid);
        try {
            boolean result = grid.validate();
            Assert.assertTrue(result);
            System.out.println(grid.toCsvString());
        } catch (ConstraintValidationException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void validateAnotherValidGrid() {
        Grid grid = new Grid(anotherValidGrid);
        try {
            boolean result = grid.validate();
            Assert.assertTrue(result);
            System.out.println(grid.toCsvString());
        } catch (ConstraintValidationException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void validateInsolubleGrid() {
        Grid grid = new Grid(insolubleGrid);
        try {
            boolean result = grid.validate();
            Assert.assertFalse(result);
        } catch (ConstraintValidationException e) {
            Assert.fail(e.getMessage());
        }
    }
}
