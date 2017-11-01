package com.lvg.weldercenter.se.ui

import com.lvg.weldercenter.se.ui.controllers.ApplicationFXManager
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

class ApplicationFX extends Application{

    private final String APPLICATION_TITLE = "Аттестационный центр сварщиков"
    private final String LOGO_ICON_PATH = "/img/logo64x64.png"

    @Override
    void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(ApplicationFXManager.getMainFrame()))
        primaryStage.setTitle(APPLICATION_TITLE)
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(LOGO_ICON_PATH)))
        primaryStage.show()
    }
}
