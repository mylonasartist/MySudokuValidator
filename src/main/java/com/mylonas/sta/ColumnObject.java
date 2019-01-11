package com.mylonas.sta;

class ColumnObject {

    private int size;
    private String name;

    private ColumnObject left;
    private ColumnObject right;

    private DataObject down;

    int getSize() {
        return size;
    }

    void setSize(int size) {
        this.size = size;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    ColumnObject getLeft() {
        return left;
    }

    void setLeft(ColumnObject left) {
        this.left = left;
    }

    ColumnObject getRight() {
        return right;
    }

    void setRight(ColumnObject right) {
        this.right = right;
    }

    DataObject getDown() {
        return down;
    }

    void setDown(DataObject down) {
        this.down = down;
    }
}
