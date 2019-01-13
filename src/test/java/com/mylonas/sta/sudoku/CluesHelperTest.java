package com.mylonas.sta.sudoku;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class CluesHelperTest {
    private final static String validClues =
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,1,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9";

    private final static String validCluesSomeUndefined =
            "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,,4,5,,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,,4,5,6,7,8,9\n" +
                    "1,2,3,1,,,7,8,9\n" +
                    "1,2,3,4,5,6,,8,9\n" +
                    ",,,,,,,,";

    private final static String invalidCluesNotANumber =
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,aa,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9";

    private final static String invalidCluesWrongCountInRow =
            "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9,4\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9";

    private final static String invalidCluesOutOfRangeGreater =
            "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,10,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,1,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9";

    private final static String invalidCluesOutOfRangeLess =
            "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,0,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,1,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9";

    private final static String invalidCluesMoreRows =
            "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,0,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,1,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9";

    private final static String invalidCluesLessRows =
            "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,0,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9\n" +
                    "1,2,3,4,5,6,7,8,9";

    private final static String invalidCluesEmpty = "";

    @Test
    public void getCluesFromCsvFormattedInput_validClues() {
        try {
            Integer[] clues =
                    CluesHelper.getCluesFromCsvFormattedInput(new ByteArrayInputStream(validClues.getBytes()));
            Assert.assertEquals(Integer.valueOf(1), clues[57]);
        } catch (IOException | InvalidCluesDefinitionException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getCluesFromCsvFormattedInput_validCluesSomeUndefined() {
        try {
            Integer[] clues =
                    CluesHelper.getCluesFromCsvFormattedInput(new ByteArrayInputStream(validCluesSomeUndefined.getBytes()));
            Assert.assertEquals(Integer.valueOf(1), clues[57]);
        } catch (IOException | InvalidCluesDefinitionException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getCluesFromCsvFormattedInput_invalidCluesNotANumber() {
        try {
            CluesHelper.getCluesFromCsvFormattedInput(new ByteArrayInputStream(invalidCluesNotANumber.getBytes()));
            Assert.fail("No expected exception raised.");
        } catch (IOException | InvalidCluesDefinitionException e) {
            // can be ignored here
        }
    }

    @Test
    public void getCluesFromCsvFormattedInput_invalidCluesWrongCountInRow() {
        try {
            CluesHelper.getCluesFromCsvFormattedInput(new ByteArrayInputStream(invalidCluesWrongCountInRow.getBytes()));
            Assert.fail("No expected exception raised.");
        } catch (IOException | InvalidCluesDefinitionException e) {
            // can be ignored here
        }
    }

    @Test
    public void getCluesFromCsvFormattedInput_invalidCluesOutOfRangeGreater() {
        try {
            CluesHelper.getCluesFromCsvFormattedInput(new ByteArrayInputStream(invalidCluesOutOfRangeGreater.getBytes()));
            Assert.fail("No expected exception raised.");
        } catch (IOException | InvalidCluesDefinitionException e) {
            // can be ignored here
        }
    }

    @Test
    public void getCluesFromCsvFormattedInput_invalidCluesOutOfRangeLess() {
        try {
            CluesHelper.getCluesFromCsvFormattedInput(new ByteArrayInputStream(invalidCluesOutOfRangeLess.getBytes()));
            Assert.fail("No expected exception raised.");
        } catch (IOException | InvalidCluesDefinitionException e) {
            // can be ignored here
        }
    }

    @Test
    public void getCluesFromCsvFormattedInput_invalidCluesMoreRows() {
        try {
            CluesHelper.getCluesFromCsvFormattedInput(new ByteArrayInputStream(invalidCluesMoreRows.getBytes()));
            Assert.fail("No expected exception raised.");
        } catch (IOException | InvalidCluesDefinitionException e) {
            // can be ignored here
        }
    }

    @Test
    public void getCluesFromCsvFormattedInput_invalidCluesLessRows() {
        try {
            CluesHelper.getCluesFromCsvFormattedInput(new ByteArrayInputStream(invalidCluesLessRows.getBytes()));
            Assert.fail("No expected exception raised.");
        } catch (IOException | InvalidCluesDefinitionException e) {
            // can be ignored here
        }
    }

    @Test
    public void getCluesFromCsvFormattedInput_invalidCluesEmpty() {
        try {
            CluesHelper.getCluesFromCsvFormattedInput(new ByteArrayInputStream(invalidCluesEmpty.getBytes()));
            Assert.fail("No expected exception raised.");
        } catch (IOException | InvalidCluesDefinitionException e) {
            // can be ignored here
        }
    }
}
