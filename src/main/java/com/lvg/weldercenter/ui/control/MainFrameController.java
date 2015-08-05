package com.lvg.weldercenter.ui.control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.log4j.Logger;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Victor Levchenko LVG Corp. on 08.11.14.
 */
public class MainFrameController extends GenericController{
    private static final Logger LOGGER = Logger.getLogger(MainFrameController.class);


    @FXML
    BorderPane mainPane;
    @FXML
    BorderPane logoPane;

    @FXML
    private MenuItem miWeldDetails;
    @FXML
    private MenuItem miDiameters;
    @FXML
    private MenuItem miThicknesses;
    @FXML
    private MenuItem miSteelTypes;
    @FXML
    private MenuItem miWeldMethods;
    @FXML
    private MenuItem miElectrodes;
    @FXML
    private MenuItem miWeldWires;
    @FXML
    private MenuItem miWeldGases;
    @FXML
    private MenuItem miWeldPositions;
    @FXML
    private MenuItem miEducations;
    @FXML
    private MenuItem miQualifications;
    @FXML
    private MenuItem miJobs;


    private Stage stage;
    private ControllerManager controllerManager;

    public ControllerManager getControllerManager() {
        return controllerManager;
    }

    public void setControllerManager(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }

    public BorderPane getLogoPane() {
        return logoPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("INITIALIZING MainFrame");
        logoPane.setVisible(true);
    }

    @FXML
    private void showWeldersPane(){
        LOGGER.debug("SHOWING WelderPane");
        controllerManager.showWelderPane();
    }

    @FXML
    private void showJournalPane(){
        LOGGER.debug("SHOWING JournalPane");
        controllerManager.showJournalPane();
    }

    @FXML
    private void showProtocolPane(){
        LOGGER.debug("SHOWING ProtocolPane");
        controllerManager.showProtocolPane();
    }

    @FXML
    private void showPropertiesPaneWeldPatternsTab(ActionEvent event){
        PropertiesController propertiesController = controllerManager.getPropertiesController();
        LOGGER.debug("SHOWING PropertiesPane WeldPatternType");

        if (!event.getSource().getClass().equals(MenuItem.class))
            return;
        MenuItem source = (MenuItem)event.getSource();

        if (source.equals(miWeldDetails)){
            selectWeldPatternTitlePane(propertiesController.TITLE_PANE_WELD_DETAIL);
            return;
        }
        if (source.equals(miDiameters)){
            selectWeldPatternTitlePane(propertiesController.TITLE_PANE_DIAMETER);
            return;
        }
        if (source.equals(miThicknesses)){
            selectWeldPatternTitlePane(propertiesController.TITLE_PANE_THICKNESS);
        }
        if (source.equals(miSteelTypes)){
            selectWeldPatternTitlePane(propertiesController.TITLE_PANE_STEEL_TYPE);
        }

    }

    private void selectWeldPatternTitlePane(String parameter){
        controllerManager.showPropertiesPane();
        controllerManager.getPropertiesController().showWeldPatternTab(parameter);
    }

    @FXML
    private void showPropertiesPaneWeldTab(ActionEvent event){

        LOGGER.debug("SHOWING PropertiesPane WeldMethod");
        PropertiesController propertiesController = controllerManager.getPropertiesController();

        if (!event.getSource().getClass().equals(MenuItem.class))
            return;
        MenuItem source = (MenuItem)event.getSource();

        if (source.equals(miWeldMethods)){
            selectWeldTabTitlePanes(propertiesController.TITLE_PANE_WELD_METHOD);
            return;
        }
        if (source.equals(miElectrodes)){
            selectWeldTabTitlePanes(propertiesController.TITLE_PANE_ELECTRODE);
            return;
        }
        if (source.equals(miWeldWires)){
            selectWeldTabTitlePanes(propertiesController.TITLE_PANE_WELD_WIRE);
            return;
        }
        if (source.equals(miWeldGases)){
            selectWeldTabTitlePanes(propertiesController.TITLE_PANE_WELD_GAS);
            return;
        }
        if (source.equals(miWeldPositions)){
            selectWeldTabTitlePanes(propertiesController.TITLE_PANE_WELD_POSITION);
            return;
        }


    }

    private void selectWeldTabTitlePanes(String parameter){

        controllerManager.showPropertiesPane();
        controllerManager.getPropertiesController().showWeldTab(parameter);
    }

    @FXML
    private void showPropertiesEducationEtcTab(ActionEvent event){
        PropertiesController propertiesController = controllerManager.getPropertiesController();

        if (!event.getSource().getClass().equals(MenuItem.class))
            return;
        MenuItem source = (MenuItem)event.getSource();

        if (source.equals(miEducations)){
            selectEducationEtcTabTitlePanes(propertiesController.TITLE_PANE_EDUCATION);
            return;
        }
        if (source.equals(miQualifications)){
            selectEducationEtcTabTitlePanes(propertiesController.TITLE_PANE_QUALIFICATION);
            return;
        }
        if (source.equals(miJobs)){
            selectEducationEtcTabTitlePanes(propertiesController.TITLE_PANE_JOB);
            return;
        }
    }

    private void selectEducationEtcTabTitlePanes(String parameter){
        LOGGER.debug("SHOWING PropertiesPane EducationEtc");
        controllerManager.showPropertiesPane();
        controllerManager.getPropertiesController().showEducationEtcTab(parameter);
    }

    private void showReportViewPane(){
        LOGGER.debug("SHOWING ReportViewPane");
        controllerManager.showReportViewPane();
    }

    @FXML
    private void showPropertiesOrganizations(){
        LOGGER.debug("SHOWING Properties organization");
        controllerManager.showPropertiesPane();
        controllerManager.getPropertiesController().showOrganizationTab();
    }

    private void hideAllPanes(){
    }

    @FXML
    private void exit(){
        Window source = stage.getScene().getWindow();
        source.fireEvent(new javafx.stage.WindowEvent(source, javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }


    public Stage getStage() {
        return stage;
    }

    public void showLogo(){
        mainPane.setCenter(logoPane);
    }

    public void setStageAndListeners(Stage stage) {
        this.stage = stage;
        this.stage.getScene().getWindow().addEventHandler(javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST,
                new ApplicationWindowHandler());
        LOGGER.debug("SET STAGE AND LISTENERS: root parent class is: " +
                stage.getScene().rootProperty().get().getClass());

    }

    private class ApplicationWindowHandler implements EventHandler<javafx.stage.WindowEvent>{
        @Override
        public void handle(javafx.stage.WindowEvent event) {
                Action response = Dialogs.create().owner(mainPane)
                        .title("Закрытие приложения")
                        .masthead("Приложение будет закрыто")
                        .message("Продолжить?")
                        .actions(Dialog.Actions.OK, Dialog.Actions.CANCEL)
                        .showConfirm();

                LOGGER.debug("REQUEST CLOSING: response: "+response.toString());
                if (response.equals(Dialog.Actions.CANCEL))
                    event.consume();
                if(response.equals(Dialog.Actions.OK)){
                    Platform.exit();
                    System.exit(0);
                }

        }
    }
}
