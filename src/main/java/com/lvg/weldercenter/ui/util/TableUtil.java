package com.lvg.weldercenter.ui.util;


import com.lvg.weldercenter.ui.entities.GenericEntityUI;
import javafx.scene.control.TableView;

/**
 * Created by Victor Levchenko (LVG Corp.) on 19.01.2015.
 */
public interface TableUtil<T extends GenericEntityUI> {

    public void selectFirstItem(TableView<T> tableView);
    public void selectLastItem(TableView<T> tableView);
    public void selectNextItem(TableView<T> tableView);
    public void selectPrevItem(TableView<T> tableView);
}
