package com.lvg.weldercenter.ui.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by Victor Levchenko LVG Corp. on 13.12.14.
 */
public class ControllerManager {
    private static final Logger LOGGER = Logger.getLogger(ControllerManager.class);

    private final FXMLLoader MAIN_FRAME_LOADER = new FXMLLoader(getClass().getResource("/fxml/javafx-ui.fxml"));
    private final FXMLLoader WELDERS_PANE_LOADER = new FXMLLoader(getClass().getResource("/fxml/welder-pane.fxml"));
    private final FXMLLoader JOURNAL_PANE_LOADER = new FXMLLoader(getClass().getResource("/fxml/journal-pane.fxml"));

    private Stage stage;

    private Parent mainFrame;
    private BorderPane welderPane;
    private BorderPane journalPane;

    private MainFrameController mainFrameController;
    private WelderController welderController;
    private JournalController journalController;

    public ControllerManager(Stage stage){
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Parent getMainFrame() {
        if (mainFrame == null) {
            try {
                mainFrame = (Parent)MAIN_FRAME_LOADER.load();
                return mainFrame;
            } catch (IOException e) {
                LOGGER.error("Could not load MainFrame: " + e.getMessage());
            }
        }
        return mainFrame;
    }

    public BorderPane getWelderPane() {
        if (welderPane == null) {
            try {
                welderPane = (BorderPane)WELDERS_PANE_LOADER.load();
                return welderPane;
            } catch (IOException e) {
                LOGGER.error("Could not load WelderPane: " + e.getMessage());
            }
        }
        return welderPane;
    }

    public BorderPane getJournalPane() {
        if (journalPane == null) {
            try {
                journalPane = (BorderPane)JOURNAL_PANE_LOADER.load();
                return journalPane;
            } catch (IOException e) {
                LOGGER.error("GET JOURNAL PANE: Could not load JournalPane: " + e.getMessage());
                LOGGER.error("GET JOURNAL PANE: exception: ",e);
            }
        }
        return journalPane;
    }

    public WelderController getWelderController() {
        if (welderController == null) {
            getWelderPane();
            welderController = (WelderController)WELDERS_PANE_LOADER.getController();
            return welderController;
        }
        return welderController;
    }

    public JournalController getJournalController() {
        if (journalController == null) {
            getJournalPane();
            journalController = (JournalController)JOURNAL_PANE_LOADER.getController();
            return journalController;
        }
        return journalController;
    }

    public MainFrameController getMainFrameController() {
        if (mainFrameController == null) {
            mainFrameController = (MainFrameController)MAIN_FRAME_LOADER.getController();
            return mainFrameController;
        }
        return mainFrameController;
    }

    public void showWelderPane(){
            BorderPane root = getWelderPane();
            BorderPane mainPane = (BorderPane)stage.getScene().rootProperty().get();
            mainPane.setCenter(root);
            root.setVisible(true);
    }

    public void showJournalPane(){
            BorderPane root = getJournalPane();
            BorderPane mainPane = (BorderPane)stage.getScene().rootProperty().get();
            mainPane.setCenter(root);
            root.setVisible(true);
    }
}
