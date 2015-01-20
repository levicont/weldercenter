package com.lvg.weldercenter.ui.util.managers;

import com.lvg.weldercenter.ui.entities.GenericEntityUI;
import com.lvg.weldercenter.ui.util.TableUtil;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Created by Victor Levchenko (LVG Corp.) on 19.01.2015.
 */
public class TableViewManager<T extends GenericEntityUI> implements TableUtil<GenericEntityUI> {

    @Override
    public void selectFirstItem(TableView<GenericEntityUI> tableView) {
        if(!tableView.getItems().isEmpty()){
            tableView.getSelectionModel().clearSelection();
            tableView.getSelectionModel().selectFirst();
            tableView.requestFocus();
            Event.fireEvent(tableView, new MouseEvent(MouseEvent.MOUSE_CLICKED,
                    0, 0, 0, 0, MouseButton.PRIMARY, 1,
                    true, true, true, true, true, true, true, true, true, true, null));
        }
    }

    @Override
    public void selectLastItem(TableView<GenericEntityUI> tableView) {
        if(!tableView.getItems().isEmpty()){
            tableView.getSelectionModel().clearSelection();
            tableView.getSelectionModel().selectLast();
            tableView.requestFocus();
            Event.fireEvent(tableView, new MouseEvent(MouseEvent.MOUSE_CLICKED,
                    0,0, 0, 0, MouseButton.PRIMARY, 1,
                    true, true, true, true,true, true, true, true, true, true, null));
        }
    }

    @Override
    public void selectNextItem(TableView<GenericEntityUI> tableView) {
        if(!tableView.getItems().isEmpty()){

            tableView.getSelectionModel().selectNext();
            if(tableView.getSelectionModel().getSelectedItems().size()>1){
                ObservableList<GenericEntityUI> selectedItems = tableView.getSelectionModel().getSelectedItems();
                GenericEntityUI lastItem = selectedItems.get(selectedItems.size()-1);
                tableView.getSelectionModel().clearSelection();
                tableView.getSelectionModel().select(lastItem);
            }
            tableView.requestFocus();
            Event.fireEvent(tableView, new MouseEvent(MouseEvent.MOUSE_CLICKED,
                    0,0, 0, 0, MouseButton.PRIMARY, 1,
                    true, true, true, true,true, true, true, true, true, true, null));
        }
    }

    @Override
    public void selectPrevItem(TableView<GenericEntityUI> tableView) {
        if(!tableView.getItems().isEmpty()){
            tableView.getSelectionModel().selectPrevious();
            if(tableView.getSelectionModel().getSelectedItems().size()>1){
                ObservableList<GenericEntityUI> selectedItems = tableView.getSelectionModel().getSelectedItems();
                GenericEntityUI firstItem = selectedItems.get(0);
                tableView.getSelectionModel().clearSelection();
                tableView.getSelectionModel().select(firstItem);
            }
            tableView.requestFocus();
            Event.fireEvent(tableView, new MouseEvent(MouseEvent.MOUSE_CLICKED,
                    0,0, 0, 0, MouseButton.PRIMARY, 1,
                    true, true, true, true,true, true, true, true, true, true, null));
        }
    }
}
