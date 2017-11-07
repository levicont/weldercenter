package com.lvg.weldercenter.se.ui.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WelderController{

    @Autowired
    WelderTableController welderTableController

    @FXML
    void refresh(ActionEvent event) {
        welderTableController.refreshTable()
    }

}
