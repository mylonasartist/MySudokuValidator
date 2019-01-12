package com.mylonas.sta.sudoku;

import com.mylonas.sta.sudoku.model.Grid;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MySudokuValidator {

    public static void main(String[] args) {
        /*int exitStatus;
        if (args.length > 0) {
            try (InputStream input = new FileInputStream(args[0])) {
                Integer[][] clues = CluesHelper.getCluesFromCsvFormattedInput(input);
                try {
                    new Grid(clues).validate();
                    System.out.println("Grid valid :)");
                    exitStatus = 0;
                } catch (ConstraintValidationException e) {
                    System.out.println("Grid invalid :( " + e.getMessage());
                    exitStatus = 1;
                }
            }
            catch (IOException ioExc) {
                System.out.println("File " + args[0] + " cannot be read.");
                exitStatus = 1;
            }
            catch (InvalidCluesDefinitionException cluesExc) {
                System.out.println("Invalid clues definition: " + cluesExc.getMessage() + ". Please re-check clues file.");
                exitStatus = 1;
            }
        }
        else {
            System.out.println("No name of file with clues in the input, " +
                    "please provide file name as the first command line argument.");
            exitStatus = 1;
        }
        System.exit(exitStatus);*/
    }
}
