package com.lvg.weldercenter.se.ui.preloader

import javafx.application.Preloader
import javafx.application.Preloader.StateChangeNotification.Type
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import javafx.stage.StageStyle

class ApplicationPreloaderFX extends Preloader{

    private Stage preloadStage
    private Parent root

    @Override
    void start(Stage stage) throws Exception {
        this.preloadStage = stage
        stage.initStyle(StageStyle.UNDECORATED)
        stage.setScene(new Scene(root))
        stage.show()
    }

    @Override
    void init() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource('/fxml/se/preloader-pane.fxml'))
        root = loader.load()
    }

    @SuppressWarnings("UnnecessaryQualifiedReference")
    @Override
    void handleStateChangeNotification(Preloader.StateChangeNotification info) {
        if (info.getType() == Type.BEFORE_START)
            preloadStage.hide()
    }
}
