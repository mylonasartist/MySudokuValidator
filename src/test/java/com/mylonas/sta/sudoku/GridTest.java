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

    @Test
    public void validateValidGrid() {
        Grid grid = new Grid(validGrid);
        try {
            boolean result = grid.validate();
            Assert.assertTrue(result);
        } catch (ConstraintValidationException e) {
            Assert.fail(e.getMessage());
    }
    }
}
