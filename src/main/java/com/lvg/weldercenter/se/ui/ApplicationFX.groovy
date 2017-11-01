package com.lvg.weldercenter.se.ui

import com.lvg.weldercenter.se.ui.controllers.ApplicationFXManager
import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
class ApplicationFX extends Application{

    private final String APPLICATION_TITLE = "Аттестационный центр сварщиков"
    private final String LOGO_ICON_PATH = "/img/logo64x64.png"

    private ConfigurableApplicationContext springContext
    private Parent rootNode

    static void main(String[] args) {
        launch(ApplicationFX.class, args)
    }

    @Override
    void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(rootNode))
        primaryStage.setTitle(APPLICATION_TITLE)
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(LOGO_ICON_PATH)))
        primaryStage.show()
    }

    @Override
    void init() throws Exception {
        springContext = SpringApplication.run(ApplicationFX.class)
        ApplicationFXManager.mainFrameFXMLLoader.setControllerFactory({e -> springContext.getBean(e)})
        rootNode = ApplicationFXManager.mainFrameFXMLLoader.load()
    }

    @Override
    void stop() throws Exception {
        springContext.stop()
    }
}
