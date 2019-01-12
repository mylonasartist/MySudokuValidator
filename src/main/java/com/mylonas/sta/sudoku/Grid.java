package com.mylonas.sta.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Grid {

    private final List<List<DataObject>> rows;
    private final List<List<DataObject>> columns;
    private final List<List<DataObject>> boxes;

    Grid(Integer[][] clues) {
        rows = new ArrayList<>(9);
        columns = new ArrayList<>(9);
        boxes = new ArrayList<>(9);

        DataObject[][] dataObjects = cluesToDataObjects(clues);

        fillRows(dataObjects);
        fillColumns(dataObjects);
        fillBoxes(dataObjects);
    }

    private DataObject[][] cluesToDataObjects(Integer[][] clues) {
        DataObject[][] data = new DataObject[clues.length][];
        for (int rowIndex = 0; rowIndex < clues.length; rowIndex++) {
            data[rowIndex] = new DataObject[clues[rowIndex].length];
            for (int columnIndex = 0; columnIndex < clues[rowIndex].length; columnIndex++) {
                data[rowIndex][columnIndex] =
                        new DataObject(clues[rowIndex][columnIndex], rowIndex, columnIndex);
            }
        }
        return data;
    }

    boolean validate() throws ConstraintValidationException {
        boolean result = true;

        ValidationHelper.validateRowConstraint(this);
        ValidationHelper.validateColumnConstraint(this);
        ValidationHelper.validateBoxConstraint(this);

        // find cell to start from.
        DataObject startCell = getNextEmptyCell(0, 0);
        if (startCell != null) {
            result = fillCellAndValidate(startCell, 1);
        }

        return result;
    }

    private boolean fillCellAndValidate(DataObject cell, Integer value) {
        cell.setValue(value);
        try {
            List<DataObject> row = rows.get(cell.getRowIndex());
            ValidationHelper.validateDuplication(row);
            List<DataObject> column = columns.get(cell.getColumnIndex());
            ValidationHelper.validateDuplication(column);
            List<DataObject> box = determineBox(cell.getRowIndex(), cell.getColumnIndex());
            ValidationHelper.validateDuplication(box);
        }
        catch (ConstraintValidationException e) {
            if (value == 9) {
                cell.clean();
                return false;
            }
            else {
                return fillCellAndValidate(cell, value + 1);
            }
        }

        DataObject nextCell = getNextEmptyCell(cell.getRowIndex(), cell.getColumnIndex());
        if (nextCell != null) {
            boolean result = fillCellAndValidate(nextCell, 1);
            if (! result) {
                if (value == 9) {
                    cell.clean();
                    return false;
                }
                else {
                    return fillCellAndValidate(cell, value + 1);
                }
            }
            return result;
        }
        else {
            return true;
        }
    }

    private DataObject getNextEmptyCell(int startRowIndex, int startColumnIndex) {
        DataObject emptyCell = null;
        for (int rowIndex = startRowIndex; rowIndex < rows.size(); rowIndex++) {
            for (int columnIndex = startColumnIndex; columnIndex < columns.size(); columnIndex++) {
                if (rows.get(rowIndex).get(columnIndex).isEmpty()) {
                    emptyCell = rows.get(rowIndex).get(columnIndex);
                    break;
                }
            }
            if (emptyCell != null) {
                break;
            }
        }
        return emptyCell;
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

    List<List<DataObject>> getBoxes() {
        return boxes;
    }

    List<List<DataObject>> getColumns() {
        return columns;
    }

    List<List<DataObject>> getRows() {
        return rows;
    }
}
