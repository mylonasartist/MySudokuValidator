package com.mylonas.sta;

public class DataObject {

    private DataObject left;
    private DataObject right;
    private DataObject up;
    private DataObject down;

    private ColumnObject columnHeader;

    DataObject getLeft() {
        return left;
    }

    void setLeft(DataObject left) {
        this.left = left;
    }

    DataObject getRight() {
        return right;
    }

    void setRight(DataObject right) {
        this.right = right;
    }

    DataObject getUp() {
        return up;
    }

    void setUp(DataObject up) {
        this.up = up;
    }

    DataObject getDown() {
        return down;
    }

    void setDown(DataObject down) {
        this.down = down;
    }

    ColumnObject getColumnHeader() {
        return columnHeader;
    }

    void setColumnHeader(ColumnObject columnHeader) {
        this.columnHeader = columnHeader;
    }
}
