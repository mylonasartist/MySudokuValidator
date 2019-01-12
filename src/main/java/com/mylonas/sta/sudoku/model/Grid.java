package com.mylonas.sta.sudoku.model;

import com.mylonas.sta.sudoku.validator.BoxConstraintValidator;
import com.mylonas.sta.sudoku.validator.ColumnConstraintValidator;
import com.mylonas.sta.sudoku.validator.ConstraintValidationException;
import com.mylonas.sta.sudoku.validator.ConstraintValidator;
import com.mylonas.sta.sudoku.validator.RowConstraintValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        DataObject[][] dataObjects = cluesToDataObjects(clues);

        fillRows(dataObjects);
        fillColumns(dataObjects);
        fillBoxes(dataObjects);
    }

    private DataObject[][] cluesToDataObjects(Integer[][] clues) {
        return Arrays.stream(clues).map(row ->
                Arrays.stream(row).map(DataObject::new).toArray(DataObject[]::new)).toArray(DataObject[][]::new);
    }

    public void validate() throws ConstraintValidationException {
        rowValidator.validate(this);
        columnValidator.validate(this);
        boxValidator.validate(this);
    }

    private void fillBoxes(DataObject[][] data) {
        for (int i = 0; i < 9; i++) {
            List<DataObject> currentBox = new ArrayList<>();
            boxes.add(currentBox);
        }
        for (int rowIndex = 0; rowIndex < data.length; rowIndex++) {
            DataObject[] currentRow = data[rowIndex];
            for (int columnIndex = 0; columnIndex < currentRow.length; columnIndex++) {
                DataObject currentValue = data[rowIndex][columnIndex];
                List<DataObject> currentBox = determineBox(rowIndex, columnIndex);
                currentBox.add(currentValue);
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

    private void fillRows(DataObject[][] data) {
        Arrays.stream(data).forEach(row -> rows.add(Arrays.asList(row)));
    }

    private void fillColumns(DataObject[][] data) {
        for (int i = 0; i < 9; i++) {
            List<DataObject> currentColumn = new ArrayList<>(9);
            for (DataObject[] currentRow : data) {
                currentColumn.add(currentRow[i]);
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
