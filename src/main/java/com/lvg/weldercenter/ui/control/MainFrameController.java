package com.lvg.weldercenter.ui.control;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.log4j.Logger;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Victor Levchenko LVG Corp. on 08.11.14.
 */
public class MainFrameController implements Initializable{
    private static final Logger LOGGER = Logger.getLogger(MainFrameController.class);

    @FXML
    BorderPane welderPane;

    @FXML
    BorderPane mainPane;

    @FXML
    Label lbAppTitle;

    private Stage stage;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welderPane.setVisible(false);
        LOGGER.debug("stage: "+getStage());


    }

    @FXML
    private void showWeldersPane(){
        if(!welderPane.isVisible()){
            welderPane.setVisible(true);
        }
    }

    @FXML
    private void exit(){
        Window source = stage.getScene().getWindow();
        source.fireEvent(new javafx.stage.WindowEvent(source, javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public Stage getStage() {
        return stage;
    }

    public void setStageAndListeners(Stage stage) {
        this.stage = stage;
        this.stage.getScene().getWindow().addEventHandler(javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST,
                new ApplicationWindowHandler());

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
                if(response == Dialog.Actions.OK){
                    Platform.exit();
                }

        }
    }
}
