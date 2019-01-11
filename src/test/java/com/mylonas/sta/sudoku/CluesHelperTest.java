package com.mylonas.sta.sudoku;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class CluesHelperTest {
    private static String validClues =
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,1,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9";

    private static String invalidCluesNotANumber =
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,aa,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9";

    private static String invalidCluesWrongCountInRow =
            "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9,4\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9";

    @Test
    public void testGetCluesFromCsvFormattedInput_validClues() {
        try {
            int[][] clues = CluesHelper.getCluesFromCsvFormattedInput(new ByteArrayInputStream(validClues.getBytes()));
            Assert.assertEquals(1, clues[6][3]);
        } catch (IOException | InvalidCluesDefinitionException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testGetCluesFromCsvFormattedInput_invalidCluesNotANumber() {
        try {
            int[][] clues = CluesHelper.getCluesFromCsvFormattedInput(new ByteArrayInputStream(invalidCluesNotANumber.getBytes()));
            Assert.fail("No exspected exception raised.");
        } catch (IOException | InvalidCluesDefinitionException e) {
        }
    }

    @Test
    public void testGetCluesFromCsvFormattedInput_invalidCluesWrongCountInRow() {
        try {
            int[][] clues = CluesHelper.getCluesFromCsvFormattedInput(new ByteArrayInputStream(invalidCluesWrongCountInRow.getBytes()));
            Assert.fail("No exspected exception raised.");
        } catch (IOException | InvalidCluesDefinitionException e) {
        }
    }
}
