package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.ui.services.LoadingWeldersService
import com.lvg.weldercenter.se.ui.views.LoadingView
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.control.MenuItem
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MainFrameController{

    @Autowired
    LoadingWeldersService loadingWeldersService

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

    private Stage loadingWeldersStage

    @FXML
    void showWelderPane(ActionEvent event) {
        FXMLLoader loader = fxmlLoaderProvider.getFXMLLoader(FXMLLoaderProvider.WELDERS_PANE_FXML_PATH)
        Parent welderPane = loader.load()
        mainPane.center = welderPane
        getLoadingWeldersStage()
        startLoadingWeldersService()

    }

    private Stage getLoadingWeldersStage(){
        if (loadingWeldersStage != null)
            return loadingWeldersStage
        loadingWeldersStage = new LoadingView(mainPane.getScene().getWindow(),loadingWeldersService)
        return loadingWeldersStage
    }

    private void startLoadingWeldersService(){
        if(loadingWeldersService.onceStarted){
            loadingWeldersService.restart()
        }else {
            loadingWeldersService.onceStarted = true
            loadingWeldersService.start()
        }
    }
}
