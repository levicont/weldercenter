package com.lvg.weldercenter.ui.button;

import java.awt.event.ActionEvent;

/**
 * Created by Victor Levchenko LVG Corp. on 26.10.14.
 */
public class AddNewButtonActionListener extends ListTableActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        list.add("New item");
        table.revalidate();
    }
}
