package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.ui.services.LoadingWeldersForTableViewService
import com.lvg.weldercenter.se.ui.services.LoadingWeldersService
import com.lvg.weldercenter.se.ui.services.OnceStartedFlag
import com.lvg.weldercenter.se.ui.views.LoadingView
import com.lvg.weldercenter.se.ui.views.LoadingViewFactory
import javafx.application.Platform
import javafx.concurrent.Service
import javafx.concurrent.Worker
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.control.MenuItem
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MainFrameController implements Initializable{

    @Autowired
    LoadingWeldersService loadingWeldersService
    @Autowired
    LoadingViewFactory loadingViewFactory
    @Autowired
    LoadingWeldersForTableViewService loadingWeldersForTableViewService

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

    private LoadingView loadingView

    @Override
    void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void showWelderPane(ActionEvent event) {
        FXMLLoader loader = fxmlLoaderProvider.getFXMLLoader(FXMLLoaderProvider.WELDERS_PANE_FXML_PATH)
        Parent welderPane = loader.load()
        mainPane.center = welderPane
        loadingViewInit(loadingWeldersForTableViewService)
        startService(loadingWeldersForTableViewService)

    }

    Stage loadingViewInit(Worker worker){
        loadingView = loadingViewFactory.getLoadingView(mainPane.getScene().getWindow(), worker)
        return loadingView
    }

    private static void startService(OnceStartedFlag service){
        def s = (Service)service
        if(service.isStartedOnce()){
            s.restart()
        }else {
            service.setStartedOnceFlag(true)
            s.start()
        }
    }
}
