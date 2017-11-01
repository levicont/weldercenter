package com.lvg.weldercenter.se.ui.controllers

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import org.apache.log4j.Logger

class ApplicationFXManager {
    private static final Logger LOGGER = Logger.getLogger(ApplicationFXManager.class)

    private static
    final FXMLLoader MAIN_FRAME_LOADER = new FXMLLoader(getClass().getResource('/fxml/se/main-frame-pane.fxml'))

    private static
    final FXMLLoader WELDERS_PANE_LOADER = new FXMLLoader(getClass().getResource('/fxml/se/welder-pane.fxml'))

    private static
    final FXMLLoader JOURNAL_PANE_LOADER = new FXMLLoader(getClass().getResource("/fxml/se/journal-pane.fxml"))

    private static
    final FXMLLoader PROTOCOL_PANE_LOADER = new FXMLLoader(getClass().getResource("/fxml/se/protocol-pane.fxml"))

    private static
    final FXMLLoader REPORT_VIEW_PANE_LOADER = new FXMLLoader(getClass().getResource("/fxml/se/report-view-pane.fxml"))

    private static
    final FXMLLoader PROPERTIES_PANE_LOADER = new FXMLLoader(getClass().getResource("/fxml/se/properties-pane.fxml"))

    private static Parent mainFrameParent
    private static BorderPane welderPane
    private static BorderPane journalPane
    private static BorderPane protocolPane
    private static BorderPane reportViewPane
    private static BorderPane propertiesPane

    private static WelderController welderController
    private static JournalController journalController
    private static ProtocolController protocolController
    private static MainFrameController mainFrameController
    private static ReportViewController reportViewController
    private static PropertiesController propertiesController


    static getMainFrameFXMLLoader(){
        MAIN_FRAME_LOADER
    }

    static Parent getMainFrame() {
        if (mainFrameParent == null) {
            try {
                mainFrameParent = MAIN_FRAME_LOADER.load()
            } catch (Exception ex) {
                LOGGER.error("GET MAIN FRAME: Cannot load main frame from fxml, message: " + ex.getMessage(), ex)
            }
        }
        mainFrameParent
    }

    static BorderPane getWelderPane() {
        if (welderPane == null) {
            try {
                welderPane = WELDERS_PANE_LOADER.load()
                return welderPane
            } catch (IOException e) {
                LOGGER.error("GET WELDER PANE: Cannot load WelderPane: " + e.getMessage())
                LOGGER.error("GET WELDER PANE: exception: ", e)
            }
        }
        return welderPane
    }

    static BorderPane getJournalPane() {
        if (journalPane == null) {
            try {
                journalPane = JOURNAL_PANE_LOADER.load()
                return journalPane
            } catch (IOException e) {
                LOGGER.error("GET JOURNAL PANE: Could not load JournalPane: " + e.getMessage())
                LOGGER.error("GET JOURNAL PANE: exception: ", e)
            }
        }
        return journalPane
    }

    static BorderPane getProtocolPane() {
        if (protocolPane == null) {
            try {
                protocolPane = (BorderPane) PROTOCOL_PANE_LOADER.load()
                return protocolPane
            } catch (IOException e) {
                LOGGER.error("GET PROTOCOL PANE: Could not load ProtocolPane: " + e.getMessage())
                LOGGER.error("GET PROTOCOL PANE: exception: ", e)
            }
        }
        return protocolPane
    }

    static BorderPane getReportViewPane() {
        if (reportViewPane == null) {
            try {
                reportViewPane = REPORT_VIEW_PANE_LOADER.load()
                return reportViewPane
            } catch (IOException e) {
                LOGGER.error("GET REPORT VIEW PANE: Could not load ReportViewPane: " + e.getMessage())
                LOGGER.error("GET REPORT VIEW PANE: exception: ", e)
            }
        }
        return reportViewPane
    }

    static BorderPane getPropertiesPane() {
        if (propertiesPane == null) {
            try {
                propertiesPane = (BorderPane) PROPERTIES_PANE_LOADER.load()
                return propertiesPane
            } catch (IOException ex) {
                LOGGER.error("GET PROPERTIES PANE: Could not load ReportViewPane: " + ex.getMessage())
                LOGGER.error("GET PROPERTIES PANE: exception: ", ex)
            }

        }
        return propertiesPane
    }

    static WelderController getWelderController() {
        if (welderController == null) {
            getWelderPane()
            welderController = WELDERS_PANE_LOADER.getController()
            return welderController
        }
        return welderController
    }

    static JournalController getJournalController() {
        if (journalController == null) {
            getJournalPane()
            journalController = JOURNAL_PANE_LOADER.getController()
            return journalController
        }
        return journalController
    }

    static ProtocolController getProtocolController() {
        if (protocolController == null) {
            getProtocolPane()
            protocolController = PROTOCOL_PANE_LOADER.getController()
            return protocolController
        }
        return protocolController
    }

    static MainFrameController getMainFrameController() {
        if (mainFrameController == null) {
            mainFrameController = MAIN_FRAME_LOADER.getController()
            return mainFrameController
        }
        return mainFrameController
    }

    static ReportViewController getReportViewController() {
        if (reportViewController == null) {
            getReportViewPane()
            reportViewController = REPORT_VIEW_PANE_LOADER.getController()
            return reportViewController
        }
        return reportViewController
    }

    static PropertiesController getPropertiesController() {
        if (propertiesController == null) {
            getPropertiesPane()
            propertiesController = PROPERTIES_PANE_LOADER.getController()
            return propertiesController
        }
        return propertiesController
    }

    static void showWelderPane(Stage stage) {
        BorderPane root = getWelderPane()
        BorderPane mainPane = (BorderPane) stage.getScene().rootProperty().get()
        mainPane.setCenter(root)
        root.setVisible(true)
    }

    static void showJournalPane(Stage stage) {
        BorderPane root = getJournalPane()
        BorderPane mainPane = (BorderPane) stage.getScene().rootProperty().get()
        mainPane.setCenter(root)
        root.setVisible(true)
    }

    static void showProtocolPane(Stage stage) {
        BorderPane root = getProtocolPane()
        BorderPane mainPane = (BorderPane) stage.getScene().rootProperty().get()
        mainPane.setCenter(root)
        root.setVisible(true)
    }

    static void showReportViewPane(Stage stage) {
        BorderPane root = getReportViewPane()
        BorderPane mainPane = (BorderPane) stage.getScene().rootProperty().get()
        mainPane.setCenter(root)
        root.setVisible(true)
    }

    static void showPropertiesPane(Stage stage) {
        BorderPane root = getPropertiesPane()
        BorderPane mainPane = (BorderPane) stage.getScene().rootProperty().get()
        mainPane.setCenter(root)
        root.setVisible(true)
    }
}
