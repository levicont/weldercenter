package com.lvg.weldercenter.ui.util;

import com.lvg.weldercenter.config.R;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.Optional;

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

    public static Optional<ButtonType> getResponseDeleteDialog(int countOfDeletingRecords){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Удаление записи");
        alert.setHeaderText("Производиться попытка удалить запись(и)");
        alert.setContentText("Вы точно хотите удалить выбранные записи? ("+ countOfDeletingRecords+"шт.");

        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }

    public static Optional<ButtonType> getResponseSaveRecordDialog(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Сохранение записи");
        alert.setHeaderText("Появились записи которые необходимио сохранить");
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result;

    }

    public static Optional<ButtonType> getResponseCloseApplication(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Закрытие приложения");
        alert.setHeaderText("Производиться попытка закрыть приложение");
        alert.setContentText("Вы уверены, что хотите завершить программу");

        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }

    public static void showWarningDialog(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Предупреждение");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
