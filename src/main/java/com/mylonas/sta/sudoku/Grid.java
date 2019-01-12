package com.mylonas.sta.sudoku;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

class Grid {

    private final List<List<DataObject>> rows;
    private final List<List<DataObject>> columns;
    private final List<List<DataObject>> boxes;
    private final DataObject[] plainData;

    Grid(Integer[] clues) {
        rows = new ArrayList<>(9);
        columns = new ArrayList<>(9);
        boxes = new ArrayList<>(9);
        for (int j = 0; j < 9; j++) {
            List<DataObject> currentBox = new ArrayList<>();
            boxes.add(currentBox);
        }
        plainData = new DataObject[81];

        fillData(clues);
    }

    private void fillData(Integer[] clues) {
        for (int i = 0; i < clues.length; i++) {
            DataObject dataObject = new DataObject(clues[i], i);
            if (rows.size() < dataObject.getRowIndex() + 1) {
                List<DataObject> currentRow = new ArrayList<>(9);
                rows.add(currentRow);
            }
            List<DataObject> currentRow = rows.get(dataObject.getRowIndex());
            currentRow.add(dataObject);
            if (columns.size() < dataObject.getColumnIndex() + 1) {
                List<DataObject> currentColumn = new ArrayList<>(9);
                columns.add(currentColumn);
            }
            List<DataObject> currentColumn = columns.get(dataObject.getColumnIndex());
            currentColumn.add(dataObject);
            List<DataObject> currentBox =
                    determineBox(dataObject.getRowIndex(), dataObject.getColumnIndex());
            currentBox.add(dataObject);

            plainData[i] = dataObject;
        }
    }

    boolean validate() throws ConstraintValidationException {
        boolean result = true;

        ValidationHelper.validateRowConstraint(this);
        ValidationHelper.validateColumnConstraint(this);
        ValidationHelper.validateBoxConstraint(this);

        // find cell to start from.
        DataObject startCell = getNextEmptyCell(0);
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

        DataObject nextCell = getNextEmptyCell(cell.getPlainIndex());
        if (nextCell != null) {
            boolean result = fillCellAndValidate(nextCell, 1);
            if (result) {
                return result;
            }
            else {
                if (value == 9) {
                    cell.clean();
                    return false;
                }
                else {
                    return fillCellAndValidate(cell, value + 1);
                }
            }
        }
        else {
            return true;
        }
    }

    private DataObject getNextEmptyCell(int startIndex) {
        DataObject emptyCell = null;
        for (int i = startIndex; i < plainData.length && emptyCell == null; i++) {
            if (plainData[i].isEmpty()) {
                emptyCell = plainData[i];
            }
        }
        return emptyCell;
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

    List<List<DataObject>> getBoxes() {
        return boxes;
    }

    List<List<DataObject>> getColumns() {
        return columns;
    }

    List<List<DataObject>> getRows() {
        return rows;
    }

    String toCsvString() {
        StringBuilder builder = new StringBuilder();
        rows.forEach(currentRow ->
                builder.append(StringUtils.join(currentRow, ",")).append(System.lineSeparator()));
        return builder.toString();
    }
}
