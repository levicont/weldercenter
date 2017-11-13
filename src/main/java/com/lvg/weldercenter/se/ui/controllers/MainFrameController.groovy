package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadAllEducationsChangeStateListener
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadWeldersForTableViewChangeStateListener
import com.lvg.weldercenter.se.ui.services.LoadingAllEducationsService
import com.lvg.weldercenter.se.ui.services.LoadingWeldersForTableViewService
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import com.lvg.weldercenter.se.ui.views.LoadingView
import com.lvg.weldercenter.se.ui.views.LoadingViewFactory
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
    LoadingViewFactory loadingViewFactory
    @Autowired
    LoadingWeldersForTableViewService loadingWeldersForTableViewService
    @Autowired
    LoadWeldersForTableViewChangeStateListener loadWeldersForTableViewChangeStateListener
    @Autowired
    LoadingAllEducationsService loadingAllEducationsService
    @Autowired
    LoadAllEducationsChangeStateListener loadAllEducationsChangeStateListener



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
        ServiceUtils.startService(loadingWeldersForTableViewService)
        loadEducations()

    }

    Stage loadingViewInit(Worker worker){
        loadingView = loadingViewFactory.getLoadingView(mainPane.getScene().getWindow(), worker)
        worker.stateProperty().addListener(loadWeldersForTableViewChangeStateListener)
        return loadingView
    }

    LoadingView getLoadingView(Worker worker) {
        loadingViewFactory.getLoadingView(mainPane.getScene().getWindow(), worker)
    }

    private void loadEducations(){
        loadingAllEducationsService.stateProperty().addListener(loadAllEducationsChangeStateListener)
        ServiceUtils.startService(loadingAllEducationsService)
    }
}
