package com.mylonas.sta.sudoku;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

class DataObject {
    private Integer value;
    private int index;
    private int rowIndex;
    private int columnIndex;

    DataObject(Integer value, int index) {
        this.value = value;
        this.index = index;
        rowIndex = index / 9;
        columnIndex = index - getRowIndex() * 9;
    }

    boolean isEmpty() {
        return value == null;
    }

    void setValue(Integer value) {
        this.value = value;
    }

    int getRowIndex() {
        return rowIndex;
    }

    int getColumnIndex() {
        return columnIndex;
    }

    int getPlainIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (value == null) {
            return false;
        }
        DataObject that = (DataObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        if (value == null) {
            return super.hashCode();
        }
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + StringUtils.EMPTY;
    }

    void clean() {
        value = null;
    }
}
