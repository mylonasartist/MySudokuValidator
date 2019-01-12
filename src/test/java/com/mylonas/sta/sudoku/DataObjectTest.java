package com.mylonas.sta.sudoku;

import org.junit.Assert;
import org.junit.Test;

public class DataObjectTest {

    @Test
    public void getIndices() {
        DataObject dataObject = new DataObject(null, 46);
        Assert.assertEquals(5, dataObject.getRowIndex());
        Assert.assertEquals(1, dataObject.getColumnIndex());
    }
}
