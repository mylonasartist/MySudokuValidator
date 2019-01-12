package com.mylonas.sta.sudoku;

import com.mylonas.sta.sudoku.model.Grid;
import org.junit.Test;

public class GridHelperTest {
    private static final Integer[][] rowsAndColumns = new Integer[][] {
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,6,4,5,9,7,8,6},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,8,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9},
            new Integer[] {1,2,3,4,5,6,7,8,9}
    };

    @Test
    public void tmp() {
        Grid grid = new Grid(rowsAndColumns);
        System.out.println(grid);
    }
}
