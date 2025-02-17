package com.aier.cloud.biz.ui.biz.fin.utils;

class MergeRange {
    public int startRow;
    public int endRow;
    public int startCell;
    public int endCell;
    public String lastValue;
    public int mergeCount;

    public MergeRange(String lastValue, int startRow, int endRow, int startCell, int endCell) {
        this.startRow = startRow;
        this.endRow = endRow;
        this.startCell = startCell;
        this.endCell = endCell;
        this.lastValue = lastValue;
        this.mergeCount = 1;
    }

    public void resetCount() {
        this.mergeCount = 1;
    }
}