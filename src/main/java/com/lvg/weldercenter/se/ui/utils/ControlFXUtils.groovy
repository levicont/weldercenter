package com.lvg.weldercenter.se.ui.utils

import com.lvg.weldercenter.config.R
import com.lvg.weldercenter.ui.util.EventFXUtil
import javafx.application.Platform
import javafx.beans.value.ChangeListener
import javafx.event.Event
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import org.apache.log4j.Logger

import java.time.LocalDate
import java.util.Optional


class ControlFXUtils {
    static final String SAVE_DIALOG_BUTTON_TEXT = 'Сохранить'
    static final String OK_DIALOG_BUTTON_TEXT = 'OK'
    static final String CANCEL_DIALOG_BUTTON_TEXT = 'Отмена'



    private static final Logger LOGGER = Logger.getLogger(ControlFXUtils.class)

    static void selectFirstTableRecord(TableView tableView){
        tableView.getSelectionModel().clearSelection()
        tableView.getSelectionModel().selectFirst()
        tableView.fireEvent(EventFXUtil.getMouseClickEvent())
        tableView.requestFocus()
    }

    static void selectRootTreeItem(TreeView treeView){
        treeView.getSelectionModel().clearSelection()
        treeView.getSelectionModel().select(treeView.getRoot())
        treeView.fireEvent(EventFXUtil.getMouseClickEvent())
        treeView.requestFocus()
    }

    static void selectLastTableRecord(TableView tableView){
        tableView.getSelectionModel().clearSelection()
        tableView.getSelectionModel().selectLast()
        tableView.fireEvent(EventFXUtil.getMouseClickEvent())
        tableView.requestFocus()
    }

    static void selectNextTableRecord(TableView tableView){
        tableView.getSelectionModel().selectNext()
        Object selectedItem = tableView.getSelectionModel().getSelectedItem()
        tableView.getSelectionModel().clearSelection()
        tableView.getSelectionModel().select(selectedItem)
        tableView.fireEvent(EventFXUtil.getMouseClickEvent())
        tableView.requestFocus()
    }

    static void selectPrevTableRecord(TableView tableView){
        tableView.getSelectionModel().selectPrevious()
        Object selectedItem = tableView.getSelectionModel().getSelectedItem()
        tableView.getSelectionModel().clearSelection()
        tableView.getSelectionModel().select(selectedItem)
        tableView.fireEvent(EventFXUtil.getMouseClickEvent())
        tableView.requestFocus()
    }

    static  <T> void selectItemInCombo(T item, ComboBox<T> comboBox) {
        if (item == null)
            return
        def selectedItem = null
        comboBox.items.each { i ->
            if (i == item) {
                selectedItem = i
                return
            }
        }
        if (selectedItem == null) {
            selectedItem = item
        }
        comboBox.selectionModel.select(selectedItem)
    }

    static void setDisabledTitlePane(boolean disabled, TitledPane titledPane){
        if (titledPane == null)
            return
        titledPane.setExpanded(!disabled)
        titledPane.setDisable(disabled)
    }

    static void setActiveListView(ListView listView){
        listView.getSelectionModel().selectFirst()
        listView.fireEvent(EventFXUtil.getMouseClickEvent())
        listView.requestFocus()
    }

    static void initTabPanes(Pane... panes){
        for (Pane p : panes)
            p.setStyle(R.UI.Control.GENERIC_STYLE_TAB_BACKGROUND)
    }

    static Optional<ButtonType> getResponseDeleteDialog(int countOfDeletingRecords){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION)
        alert.setTitle("Удаление записи")
        alert.setHeaderText("Производиться попытка удалить запись(и)")
        alert.setContentText("Вы точно хотите удалить выбранные записи? ("+ countOfDeletingRecords+"шт.")

        Optional<ButtonType> result = alert.showAndWait()
        return result
    }

    static Optional<ButtonType> getResponseSaveRecordDialog(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION)
        alert.setTitle("Сохранение записи")
        alert.setHeaderText("Появились записи которые необходимио сохранить")
        alert.setContentText(message)

        Optional<ButtonType> result = alert.showAndWait()
        return result

    }

    static Optional<ButtonType> getResponseCloseApplication(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION)
        alert.setTitle("Закрытие приложения")
        alert.setHeaderText("Произошла попытка закрыть приложение")
        alert.setContentText("Вы уверены, что хотите завершить программу?")

        Optional<ButtonType> result = alert.showAndWait()
        return result
    }

    static void closeApplication(){
        Optional<ButtonType> closingResponse = getResponseCloseApplication()
        if (closingResponse.get() == ButtonType.OK){
            LOGGER.info("Application is shutting down")
            Platform.exit()
        }
    }

    static void addChangeListenerToTextFields(ChangeListener<String> listener, TextField ... textFields){
        textFields.each {textField -> textField.textProperty().addListener(listener)}
    }

    static void addChangeListenerToDatePickers(ChangeListener<LocalDate> listener, DatePicker ... datePickers){
        datePickers.each {datePicker -> datePicker.valueProperty().addListener(listener)}
    }

    static <T>void addChangeListenerToComboBoxes(ChangeListener<T> listener, ComboBox<T> ... comboBoxes){
        comboBoxes.each {comboBox -> comboBox.valueProperty().addListener(listener)}
    }

    static <T>void removeChangeListenerFromComboBoxes(ChangeListener<T> listener, ComboBox<T> ... comboBoxes){
        comboBoxes.each {comboBox -> comboBox.valueProperty().removeListener(listener)}
    }

    static void removeChangeListenerFromDatePickers(ChangeListener<LocalDate> listener, DatePicker ... datePickers){
        datePickers.each {datePicker -> datePicker.valueProperty().removeListener(listener)}
    }

    static void removeChangeListenerFromTextFields(ChangeListener<String> listener, TextField ... textFields){
        textFields.each {textField -> textField.textProperty().removeListener(listener)}
    }

    static void showWarningDialog(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING)
        alert.setTitle("Предупреждение")
        alert.setHeaderText(null)
        alert.setContentText(message)
        alert.showAndWait()
    }


    static  void clearTextFields(TextField ... textFields){
        for (TextField tf : textFields)
            tf.clear()
    }

    static void clearTextAreas(TextArea ... textAreas){
        for (TextArea ta : textAreas)
            ta.clear()
    }

    static void setDisabledTextFields(boolean disabled, TextField ... textFields){
        if (disabled){
            for (TextField tf : textFields){
                tf.setEditable(false)
                tf.setStyle(R.UI.Control.GENERIC_STYLE_NOT_EDITABLE_BACKGROUND)
            }
        }else {
            for (TextField tf : textFields){
                tf.setEditable(true)
                tf.setStyle("")
            }
        }
    }

    static void setDisabledComboBoxes(boolean disabled, ComboBox ... comboBoxes){
        if (disabled){
            for (ComboBox cb : comboBoxes) {
                cb.setDisable(true)
                cb.setStyle(R.UI.Control.GENERIC_STYLE_NOT_EDIT_COMBOBOX)
            }
        }else
            for (ComboBox cb : comboBoxes) {
                cb.setDisable(false)
                cb.setStyle("")
            }
    }

    static void setDisabledDatePickers(boolean disabled, DatePicker ... datePickers){
        if (disabled){
            for (DatePicker dp : datePickers) {
                dp.setDisable(true)
                dp.setStyle(R.UI.Control.GENERIC_STYLE_NOT_EDIT_DATE_PICKER)
                setDisabledTextFields(disabled, dp.getEditor())
            }
        }else
            for (DatePicker dp : datePickers) {
                setDisabledTextFields(disabled, dp.getEditor())
                dp.setDisable(false)
                dp.setStyle("")
            }
    }

    static void setDisabledTextAreas(boolean disable, TextArea ... textAreas){
        if (disable){
            for (TextArea ta : textAreas){
                ta.setEditable(false)
                ta.setStyle(R.UI.Control.GENERIC_STYLE_NOT_EDITABLE_BACKGROUND)
                ta.setOpacity(0.7)
            }
        }else {
            for (TextArea ta : textAreas){
                ta.setEditable(true)
                ta.setStyle("")
                ta.setOpacity(1.0)
            }
        }
    }

    static void setDisabledTableViews(boolean disable, TableView ... tableViews){
        if (disable){
            for (TableView tableView : tableViews){
                //tableView.setDisable(disable);
                tableView.setStyle(R.UI.Control.GENERIC_STYLE_NOT_EDIT_TABLE_VIEW)

            }
        }else {
            for (TableView tableView : tableViews){
                tableView.setDisable(disable)
                tableView.setStyle("")
            }
        }
    }

    static void setDisabledListViews(boolean disable, ListView ... listViews){
        if (disable){
            for (ListView listView : listViews){
                //listView.setDisable(disable);
                listView.setStyle(R.UI.Control.GENERIC_STYLE_NOT_EDIT_TABLE_VIEW)
            }
        }else {
            for (ListView listView : listViews){
                listView.setDisable(disable)
                listView.setStyle("")
            }
        }
    }

    static void setDisabledButtons(boolean disable, Button ... buttons){
        for (Button bt : buttons){
            bt.setDisable(disable)
        }
    }

    static boolean isEventIsSelectedKeyOnList(Event event){
        if (event.getClass() == KeyEvent.class) {
            if (((KeyEvent) event).getCode() == KeyCode.UP ||
                    ((KeyEvent) event).getCode() == KeyCode.DOWN ||
                    ((KeyEvent) event).getCode() == KeyCode.PAGE_UP ||
                    ((KeyEvent) event).getCode() == KeyCode.PAGE_DOWN ||
                    ((KeyEvent) event).getCode() == KeyCode.HOME ||
                    ((KeyEvent) event).getCode() == KeyCode.END){
                return true
            }
        }
        return false
    }

    static boolean isEventIsSelectedMouse(Event event){
        if (event.getClass() == MouseEvent.class) {
            if (((MouseEvent) event).getEventType() == MouseEvent.MOUSE_CLICKED) {
                return true
            }
        }
        return false
    }


}
