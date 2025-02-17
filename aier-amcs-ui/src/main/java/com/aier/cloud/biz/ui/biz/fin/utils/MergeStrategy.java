package com.aier.cloud.biz.ui.biz.fin.utils;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MergeStrategy extends AbstractMergeStrategy {

    private Set<Integer> mergeCellIndex = new HashSet<>();
    private Integer maxRow = 0;

    private MergeStrategy() {
    }

    public MergeStrategy(Integer maxRow, int... mergeCellIndex) {
        Arrays.stream(mergeCellIndex).forEach(this.mergeCellIndex::add);
        this.maxRow = maxRow;
    }

    private Map<Integer, MergeRange> lastRow = new HashedMap();

    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {
        int currentCellIndex = cell.getColumnIndex();
        if (mergeCellIndex.contains(currentCellIndex)) {
            String currentCellValue = cell.getStringCellValue();
            int currentRowIndex = cell.getRowIndex();
            if (!lastRow.containsKey(currentCellIndex)) {
                lastRow.put(currentCellIndex, new MergeRange(currentCellValue, currentRowIndex, currentRowIndex, currentCellIndex, currentCellIndex));
                return;
            }
            MergeRange mergeRange = lastRow.get(currentCellIndex);
            if (!mergeRange.lastValue.equals(currentCellValue)) {
                if (mergeRange.startRow != mergeRange.endRow) {
                    sheet.addMergedRegionUnsafe(new CellRangeAddress(mergeRange.startRow, mergeRange.endRow, mergeRange.startCell, mergeRange.endCell));
                }
                lastRow.put(currentCellIndex, new MergeRange(currentCellValue, currentRowIndex, currentRowIndex, currentCellIndex, currentCellIndex));
                mergeRange.resetCount();
            } else if (++mergeRange.mergeCount % 3 == 0) { // Adjusted logic for merging every alternate row with the same data
                sheet.addMergedRegionUnsafe(new CellRangeAddress(mergeRange.startRow, currentRowIndex - 1, mergeRange.startCell, mergeRange.endCell));
                mergeRange.startRow = currentRowIndex;
                mergeRange.resetCount();
            }
            mergeRange.endRow = currentRowIndex;
            if (relativeRowIndex.equals(maxRow - 1)) {
                mergeRange = lastRow.get(currentCellIndex);
                if (mergeRange.startRow != mergeRange.endRow) {
                    sheet.addMergedRegionUnsafe(new CellRangeAddress(mergeRange.startRow, mergeRange.endRow, mergeRange.startCell, mergeRange.endCell));
                }
            }
        }
    }
}

