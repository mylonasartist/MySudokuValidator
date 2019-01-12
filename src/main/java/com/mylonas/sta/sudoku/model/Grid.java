package com.mylonas.sta.sudoku.model;

import com.mylonas.sta.sudoku.validator.BoxConstraintValidator;
import com.mylonas.sta.sudoku.validator.ColumnConstraintValidator;
import com.mylonas.sta.sudoku.validator.ConstraintValidationException;
import com.mylonas.sta.sudoku.validator.ConstraintValidator;
import com.mylonas.sta.sudoku.validator.RowConstraintValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Grid {

    private static final ConstraintValidator rowValidator = new RowConstraintValidator();
    private static final ConstraintValidator columnValidator = new ColumnConstraintValidator();
    private static final ConstraintValidator boxValidator = new BoxConstraintValidator();

    private final List<List<DataObject>> rows;
    private final List<List<DataObject>> columns;
    private final List<List<DataObject>> boxes;

    public Grid(Integer[][] clues) {
        rows = new ArrayList<>(9);
        columns = new ArrayList<>(9);
        boxes = new ArrayList<>(9);

        fillRows(clues);
        fillColumns(clues);
        fillBoxes(clues);
    }

    public void validate() throws ConstraintValidationException {
        rowValidator.validate(this);
        columnValidator.validate(this);
        boxValidator.validate(this);
    }

    private void fillBoxes(Integer[][] clues) {
        for (int i = 0; i < 9; i++) {
            List<DataObject> currentBox = new ArrayList<>();
            boxes.add(currentBox);
        }
        for (int rowIndex = 0; rowIndex < clues.length; rowIndex++) {
            Integer[] currentRow = clues[rowIndex];
            for (int columnIndex = 0; columnIndex < currentRow.length; columnIndex++) {
                Integer currentValue = clues[rowIndex][columnIndex];
                List<DataObject> currentBox = determineBox(rowIndex, columnIndex);
                currentBox.add(new DataObject(currentValue));
            }
        }
    }

    private List<DataObject> determineBox(int rowIndex, int columnIndex) {
        List<List<DataObject>> rowBoxes;
        if (rowIndex <= 2) {
            rowBoxes = boxes.subList(0, 3);
        }
        else if (rowIndex <= 5) {
            rowBoxes = boxes.subList(3, 6);
        }
        else {
            rowBoxes = boxes.subList(6, 9);
        }
        return rowBoxes.get(columnIndex / 3);
    }

    private void fillRows(Integer[][] clues) {
        Arrays.stream(clues).forEach(row ->
                rows.add(Arrays.stream(row).map(DataObject::new).collect(Collectors.toList())));
    }

    private void fillColumns(Integer[][] clues) {
        for (int i = 0; i < 9; i++) {
            List<DataObject> currentColumn = new ArrayList<>(9);
            for (Integer[] currentRow : clues) {
                currentColumn.add(new DataObject(currentRow[i]));
            }
            columns.add(currentColumn);
        }
    }

    public List<List<DataObject>> getBoxes() {
        return boxes;
    }

    public List<List<DataObject>> getColumns() {
        return columns;
    }

    public List<List<DataObject>> getRows() {
        return rows;
    }
}
