package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.ui.dto.WelderUI
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextField
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WelderController{
    private static final Logger LOGGER = Logger.getLogger(WelderController.class)
    @Autowired
    WelderTableController welderTableController

    @FXML
    private TextField txfSurname

    @FXML
    private TextField txfSecname

    @FXML
    private TextField txfName


    @FXML
    void refresh(ActionEvent event) {
        welderTableController.refreshTable()
    }

    void loadWelder(WelderUI welderUI){
        if (welderUI == null) {
            LOGGER.warn('Cannot load null welderUI')
            return
        }
        txfName.textProperty().set(welderUI.name)
        txfSecname.textProperty().set(welderUI.secondName)
        txfSurname.textProperty().set(welderUI.surname)


    }

}
