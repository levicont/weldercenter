package com.lvg.weldercenter.se.ui.controllers

import com.lvg.weldercenter.se.ui.views.LoadingView
import com.lvg.weldercenter.se.ui.views.LoadingViewFactory
import javafx.concurrent.Worker
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.control.MenuItem
import javafx.scene.layout.BorderPane
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MainFrameController implements Initializable{
    private static final Logger LOGGER = Logger.getLogger(MainFrameController.class)

    @Autowired
    LoadingViewFactory loadingViewFactory



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

    @Override
    void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void showWelderPane(ActionEvent event) {
        Parent welderPane = fxmlLoaderProvider.loadParent(PaneType.WELDER_PANE, true)
        mainPane.center = welderPane
        welderPane.setVisible(true)
        LOGGER.debug("ActionEvent performed ${event.eventType} on ${event.source.class.name}")
    }

    LoadingView getLoadingView(Worker worker) {
        loadingViewFactory.getLoadingView(mainPane.getScene().getWindow(), worker)
    }

    void closePane(PaneType paneType){
        Parent pane = fxmlLoaderProvider.loadParent(paneType, true)
        pane.setVisible(false)
        mainPane.center = logoPane
        logoPane.setVisible(true)
    }


}
