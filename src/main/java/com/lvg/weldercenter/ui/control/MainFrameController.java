package com.lvg.weldercenter.ui.control;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
                }

        }
    }
}
