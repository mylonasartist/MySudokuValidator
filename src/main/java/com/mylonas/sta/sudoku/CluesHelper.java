package com.mylonas.sta.sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

class CluesHelper {
    private CluesHelper() {
    }

    static int[][] getCluesFromCsvFormattedInput(InputStream input)
            throws IOException, InvalidCluesDefinitionException {
        int[][] clues = new int[9][];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            int index = 0;
            String line = reader.readLine();
            while (line != null) {
                int[] currentRow = getRow(line);
                clues[index] = currentRow;
                index++;
                line = reader.readLine();
            }
        }
        return clues;
    }

    private static int[] getRow(String line) throws InvalidCluesDefinitionException {
        String[] values = line.split(",");
        if (values.length != 9) {
            throw new InvalidCluesDefinitionException("The clues entry line " + line +
                    " doesn't represent the row correcly. There must be 9 digits");
        }
        try {
            return Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
        }
        catch (NumberFormatException nfe) {
            throw new InvalidCluesDefinitionException("The clues entry line " + line +
                    " contains a value the cannot be converted to integer");
        }
    }
}
