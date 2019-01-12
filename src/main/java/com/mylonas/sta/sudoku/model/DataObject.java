package com.mylonas.sta.sudoku.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class DataObject {
    private Integer value;

    public DataObject(Integer value) {
        this.value = value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataObject that = (DataObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + StringUtils.EMPTY;
    }
}
