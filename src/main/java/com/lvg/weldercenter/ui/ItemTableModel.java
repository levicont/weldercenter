package com.lvg.weldercenter.ui;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 25.10.14.
 */
public class ItemTableModel extends AbstractTableModel {

    private List itemList;

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return "Items";
    }

    public void setItemList(List itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getRowCount() {
        return itemList.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return itemList.get(rowIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        itemList.set(rowIndex,aValue);
    }
}
