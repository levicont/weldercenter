package com.lvg.weldercenter.ui.button;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 26.10.14.
 */
public abstract class ListTableActionListener implements ActionListener{

    protected List list;
    protected JTable table;

    public void setList(List list) {
        this.list = list;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
}
