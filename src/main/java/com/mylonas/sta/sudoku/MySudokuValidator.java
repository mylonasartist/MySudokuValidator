package com.mylonas.sta.sudoku;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MySudokuValidator {

    public static void main(String[] args) {
        int exitStatus;
        if (args.length > 0) {
            try (InputStream input = new FileInputStream(args[0])) {
                Integer lowerCluesLimit = getLowerCluesLimit(args);
                try {
                    Integer[] clues = CluesHelper.getCluesFromCsvFormattedInput(input, lowerCluesLimit);
                    Grid grid = new Grid(clues);
                    boolean result = grid.validate();
                    if (result) {
                        System.out.println("Grid valid :)");
                        System.out.println(grid.toCsvString());
                        exitStatus = 0;
                    }
                    else {
                        System.out.println("Grid insoluble :(");
                        exitStatus = 1;
                    }
                }
                catch (InsufficientCluesException e) {
                    System.out.println(e.getMessage() +
                            ". Please try to configure lower number of clues with 2nd command line parameter.");
                    exitStatus = 1;
                }
                catch (ConstraintValidationException e) {
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
        System.exit(exitStatus);
    }

    private static Integer getLowerCluesLimit(String[] args) {
        Integer lowerCluesLimit = null;
        if (args.length > 1) {
            try {
                lowerCluesLimit = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e) {
                System.out.println("Couldn't get lower clues number limit from command line parameter: " + args[1]);
            }
        }
        return lowerCluesLimit;
    }
}
