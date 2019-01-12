package com.mylonas.sta.sudoku;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

class CluesHelper {

    private static final int LOWER_NUMBER_OF_CLUES = 17;

    private CluesHelper() {
    }

    static Integer[] getCluesFromCsvFormattedInput(InputStream input) throws IOException, InvalidCluesDefinitionException {
        return getCluesFromCsvFormattedInput(input, LOWER_NUMBER_OF_CLUES);
    }

    static Integer[] getCluesFromCsvFormattedInput(InputStream input, Integer lowerCluesLimit)
            throws IOException, InvalidCluesDefinitionException {
        Integer[] clues = new Integer[81];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            int linesIndex = 0;
            String line = reader.readLine();
            while (line != null) {
                Integer[] currentRow = getRow(line);
                for (int i = 0; i < currentRow.length; i++) {
                    clues[9 * linesIndex + i] = currentRow[i];
                }
                linesIndex++;
                line = reader.readLine();
            }
            if (linesIndex != 9) {
                throw new InvalidCluesDefinitionException("There should be 9 lines in clues definition. " +
                        "The actual amount is " + linesIndex);
            }
        }
        long cluesNumber = Arrays.stream(clues).filter(clue -> clue != null).count();
        if (lowerCluesLimit == null) {
            lowerCluesLimit = LOWER_NUMBER_OF_CLUES;
        }
        if (cluesNumber < lowerCluesLimit) {
            throw new InsufficientCluesException("The nummber of clues lower then defined: " +
                    lowerCluesLimit);
        }
        return clues;
    }

    private static Integer[] getRow(String line) throws InvalidCluesDefinitionException {
        String[] strValues = line.split(",", -1);
        if (strValues.length != 9) {
            throw new InvalidCluesDefinitionException("The clues entry line " + line +
                    " doesn't represent the row correcly. There must be 9 positions");
        }

        // not using streams, lambdas here because they won't allow to throw the checked InvalidCluesDefinitionException.
        Integer[] rowValues = new Integer[9];
        for (int i = 0; i < strValues.length; i++) {
            rowValues[i] = stringToSudokuInteger(strValues[i]);
        }
        return rowValues;
    }

    private static Integer stringToSudokuInteger(String strValue) throws InvalidCluesDefinitionException {
        if (StringUtils.isEmpty(strValue)) {
            return null;
        }
        try {
            int value = Integer.parseInt(strValue);
            if (value < 1 || value > 9) {
                throw new InvalidCluesDefinitionException("The clue value range is from 1 to 9. " +
                        "The actual clue value is " + value);
            }
            return value;
        }
        catch (NumberFormatException nfe) {
            throw new InvalidCluesDefinitionException("The clues entry line " + strValue +
                    " contains a value that cannot be converted to integer");
        }
    }
}
