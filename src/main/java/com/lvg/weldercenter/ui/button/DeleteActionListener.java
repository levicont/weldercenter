package com.lvg.weldercenter.ui.button;

import java.awt.event.ActionEvent;

/**
 * Created by Victor Levchenko LVG Corp. on 26.10.14.
 */
public class DeleteActionListener extends ListTableActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();

        if(selectedRow == -1){
            return;
        }

        if(table.isEditing()){
            return;
        }

        list.remove(selectedRow);
        table.revalidate();
    }
}
