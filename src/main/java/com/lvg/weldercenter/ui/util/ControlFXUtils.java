package com.lvg.weldercenter.ui.util;

import com.lvg.weldercenter.config.R;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

/**
 * Created by Victor on 20.08.2015.
 */
public class ControlFXUtils {


    public static void selectFirstTableRecord(TableView tableView){
        tableView.getSelectionModel().clearSelection();
        tableView.getSelectionModel().selectFirst();
        tableView.fireEvent(EventFXUtil.getMouseClickEvent());
        tableView.requestFocus();
    }
    public static void selectRootTreeItem(TreeView treeView){
        treeView.getSelectionModel().clearSelection();
        treeView.getSelectionModel().select(treeView.getRoot());
        treeView.fireEvent(EventFXUtil.getMouseClickEvent());
        treeView.requestFocus();
    }

    public static void selectLastTableRecord(TableView tableView){
        tableView.getSelectionModel().clearSelection();
        tableView.getSelectionModel().selectLast();
        tableView.fireEvent(EventFXUtil.getMouseClickEvent());
        tableView.requestFocus();
    }
    public static void selectNextTableRecord(TableView tableView){
        tableView.getSelectionModel().selectNext();
        Object selectedItem = tableView.getSelectionModel().getSelectedItem();
        tableView.getSelectionModel().clearSelection();
        tableView.getSelectionModel().select(selectedItem);
        tableView.fireEvent(EventFXUtil.getMouseClickEvent());
        tableView.requestFocus();
    }
    public static void selectPrevTableRecord(TableView tableView){
        tableView.getSelectionModel().selectPrevious();
        Object selectedItem = tableView.getSelectionModel().getSelectedItem();
        tableView.getSelectionModel().clearSelection();
        tableView.getSelectionModel().select(selectedItem);
        tableView.fireEvent(EventFXUtil.getMouseClickEvent());
        tableView.requestFocus();
    }

    public static void setDisabledTitlePane(boolean disabled, TitledPane titledPane){
        if (titledPane == null)
            return;
        titledPane.setExpanded(!disabled);
        titledPane.setDisable(disabled);
    }

    public static void setActiveListView(ListView listView){
        listView.getSelectionModel().selectFirst();
        listView.fireEvent(EventFXUtil.getMouseClickEvent());
        listView.requestFocus();
    }

    public static void initTabPanes(Pane... panes){
        for (Pane p : panes)
            p.setStyle(R.UI.Control.GENERIC_STYLE_TAB_BACKGROUND);
    }

    public static Action getResponseDeleteDialog(int countOfDeletingRecords, Object owner){
        Action response = Dialogs.create().owner(owner)
                .title("Удаление записей")
                .masthead("Сделан выбор записей для удаления")
                .message("Удалить выбранные записи? ("+countOfDeletingRecords+"шт.)")
                .actions(org.controlsfx.dialog.Dialog.Actions.OK, org.controlsfx.dialog.Dialog.Actions.CANCEL)
                .showConfirm();
        return response;
    }

    public static Action getResponseSaveRecordDialog(String message, Object owner){
        Action response = Dialogs.create().owner(owner)
                .title("Сохранение записи")
                .masthead("Появились записи которые необходимио сохранить")
                .message(message)
                .actions(org.controlsfx.dialog.Dialog.Actions.OK, org.controlsfx.dialog.Dialog.Actions.CANCEL)
                .showConfirm();
        return response;
    }

    public static  void clearTextFields(TextField ... textFields){
        for (TextField tf : textFields)
            tf.clear();
    }

    public static void clearTextAreas(TextArea ... textAreas){
        for (TextArea ta : textAreas)
            ta.clear();
    }

    public static void setDisabledTextFields(boolean disabled, TextField ... textFields){
        if (disabled){
            for (TextField tf : textFields){
                tf.setEditable(false);
                tf.setStyle(R.UI.Control.GENERIC_STYLE_NOT_EDITABLE_BACKGROUND);
            }
        }else {
            for (TextField tf : textFields){
                tf.setEditable(true);
                tf.setStyle("");
            }
        }
    }
    public static void setDisabledComboBoxes(boolean disabled, ComboBox ... comboBoxes){
        if (disabled){
            for (ComboBox cb : comboBoxes) {
                cb.setDisable(true);
                cb.setStyle(R.UI.Control.GENERIC_STYLE_NOT_EDIT_COMBOBOX);
            }
        }else
            for (ComboBox cb : comboBoxes) {
                cb.setDisable(false);
                cb.setStyle("");
            }
    }
    public static void setDisabledDatePickers(boolean disabled, DatePicker ... datePickers){
        if (disabled){
            for (DatePicker dp : datePickers) {

                dp.setDisable(true);
                dp.setStyle(R.UI.Control.GENERIC_STYLE_NOT_EDIT_DATE_PICKER);
                setDisabledTextFields(disabled, dp.getEditor());
                dp.getEditor().setStyle(R.UI.Control.GENERIC_STYLE_NOT_EDIT_DATE_PICKER);
            }
        }else
            for (DatePicker dp : datePickers) {
                setDisabledTextFields(disabled, dp.getEditor());
                dp.setDisable(false);
                dp.setStyle("");
            }
    }
    public static void setDisabledTextAreas(boolean disable, TextArea ... textAreas){
        if (disable){
            for (TextArea ta : textAreas){
                ta.setEditable(false);
                ta.setStyle(R.UI.Control.GENERIC_STYLE_NOT_EDITABLE_BACKGROUND);
                ta.setOpacity(0.7);
            }
        }else {
            for (TextArea ta : textAreas){
                ta.setEditable(true);
                ta.setStyle("");
                ta.setOpacity(1.0);
            }
        }
    }
    public static void setDisabledTableViews(boolean disable, TableView ... tableViews){
        if (disable){
            for (TableView tableView : tableViews){
                //tableView.setDisable(disable);
                tableView.setStyle(R.UI.Control.GENERIC_STYLE_NOT_EDIT_TABLE_VIEW);

            }
        }else {
            for (TableView tableView : tableViews){
                tableView.setDisable(disable);
                tableView.setStyle("");
            }
        }
    }

    public static void setDisabledListViews(boolean disable, ListView ... listViews){
        if (disable){
            for (ListView listView : listViews){
                //listView.setDisable(disable);
                listView.setStyle(R.UI.Control.GENERIC_STYLE_NOT_EDIT_TABLE_VIEW);
            }
        }else {
            for (ListView listView : listViews){
                listView.setDisable(disable);
                listView.setStyle("");
            }
        }
    }

    public static void setDisabledButtons(boolean disable, Button ... buttons){
        for (Button bt : buttons){
            bt.setDisable(disable);
        }
    }

    public static boolean isEventIsSelectedKeyOnList(Event event){
        if (event.getClass().equals(KeyEvent.class)) {
            if (((KeyEvent) event).getCode().equals(KeyCode.UP) ||
                    ((KeyEvent) event).getCode().equals(KeyCode.DOWN) ||
                    ((KeyEvent) event).getCode().equals(KeyCode.PAGE_UP) ||
                    ((KeyEvent) event).getCode().equals(KeyCode.PAGE_DOWN) ||
                    ((KeyEvent) event).getCode().equals(KeyCode.HOME) ||
                    ((KeyEvent) event).getCode().equals(KeyCode.END)){
                return true;
            }
        }
        return false;
    }

    public static boolean isEventIsSelectedMouse(Event event){
        if (event.getClass().equals(MouseEvent.class)) {
            if (((MouseEvent)event).getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                return true;
            }
        }
        return false;
    }


}
