package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.config.R;
import com.lvg.weldercenter.ui.util.managers.FormatterManager;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * Created by Victor Levchenko LVG Corp. on 13.12.14.
 */
abstract public class GenericController implements Initializable {

    private Stage stage;
    protected ControllerManager controllerManager;
    protected R.UI.Control constants = null;

    protected FormatterManager formatterManager = new FormatterManager();

    public ControllerManager getControllerManager() {
        return controllerManager;
    }

    public void setControllerManager(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }

    protected void initDatePickers(DatePicker... datePickers){
        for (DatePicker dp : datePickers){
            dp.getEditor().setTextFormatter(formatterManager.getDateTextFieldFormatter());
            dp.getEditor().textProperty().addListener(formatterManager.getCheckListener(dp));
        }
    }
}
