package com.lvg.weldercenter.ui.control;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Victor Levchenko LVG Corp. on 12.12.14.
 */
public class JournalController extends GenericController{
    private static final Logger LOGGER = Logger.getLogger(JournalController.class);

    private ControllerManager controllerManager;

    @FXML
    BorderPane mainJournalPane;

    public ControllerManager getControllerManager() {
        return controllerManager;
    }

    public void setControllerManager(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("INITIALIZING JournalPane");
    }

    @FXML
    private void closeJournalPane(){
        if(mainJournalPane.isVisible()){
            mainJournalPane.setVisible(false);
            controllerManager.getMainFrameController().showLogo();
        }

    }



}
