package com.lvg.weldercenter.ui.control;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * Created by Victor Levchenko LVG Corp. on 13.12.14.
 */
abstract public class GenericController implements Initializable {

    private Stage stage;
    protected ControllerManager controllerManager;
    protected final Integer MAX_TASK_PROGRESS_VALUE = 100;

    public ControllerManager getControllerManager() {
        return controllerManager;
    }

    public void setControllerManager(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }
}
