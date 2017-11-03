package com.lvg.weldercenter.se.ui.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.control.MenuItem
import javafx.scene.layout.BorderPane
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MainFrameController{

    @FXML
    private MenuItem miDiameters

    @FXML
    private MenuItem miEducations

    @FXML
    private BorderPane logoPane

    @FXML
    private MenuItem miThicknesses

    @FXML
    private MenuItem miElectrodes

    @FXML
    private Label lbAppTitle

    @FXML
    private MenuItem miWeldMethods

    @FXML
    private MenuItem miWelders

    @FXML
    private MenuItem miExit

    @FXML
    private MenuItem miWeldWires

    @FXML
    private MenuItem miQualifications

    @FXML
    private MenuItem miWeldGases

    @FXML
    private MenuItem miWeldPositions

    @FXML
    private MenuItem miSteelTypes

    @FXML
    private MenuItem miJobs

    @FXML
    private BorderPane mainPane

    @FXML
    private MenuItem miWeldDetails

    @Autowired
    FXMLLoaderProvider fxmlLoaderProvider

    @FXML
    void showWelderPane(ActionEvent event) {
        //TODO this have to occur in an other thread
        FXMLLoader loader = fxmlLoaderProvider.getFXMLLoader(FXMLLoaderProvider.WELDERS_PANE_FXML_PATH)
        Parent welderPane = loader.load()
        mainPane.center = welderPane
    }
}
