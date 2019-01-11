package com.mylonas.sta.sudoku;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MySudokuValidator {

    public static void main(String[] args) {
        if (args.length > 0) {
            try (InputStream input = new FileInputStream(args[0])) {
                int[][] clues = CluesHelper.getCluesFromCsvFormattedInput(input);
            }
            catch (IOException ioExc) {
                System.out.println("File " + args[0] + " cannot be read.");
            }
            catch (InvalidCluesDefinitionException cluesExc) {
                System.out.println("Invalid clues definition: " + cluesExc.getMessage() + ". Please re-check clues file.");
            }
        }
        else {
            System.out.println("No name of file with clues in the input, " +
                    "please provide file name as the first command line argument.");
        }
    }
}
