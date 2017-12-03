package com.lvg.weldercenter.se

import com.lvg.weldercenter.se.ui.controllers.FXMLLoaderProvider
import com.lvg.weldercenter.se.ui.controllers.PaneType
import com.lvg.weldercenter.se.ui.preloader.ApplicationPreloaderFX
import com.lvg.weldercenter.se.ui.utils.ControlFXUtils
import com.sun.javafx.application.LauncherImpl
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
        LauncherImpl.launchApplication(ApplicationFX.class, ApplicationPreloaderFX.class, args)
    }

    @Override
    void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(rootNode))
        primaryStage.setTitle(APPLICATION_TITLE)
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(LOGO_ICON_PATH)))
        primaryStage.setOnCloseRequest({event ->
            ControlFXUtils.closeApplication()
            event.consume()
        })
        primaryStage.show()
    }

    @Override
    void init() throws Exception {
        springContext = SpringApplication.run(ApplicationFX.class)
        FXMLLoaderProvider provider = springContext.getBean(FXMLLoaderProvider.class)
        rootNode = provider.loadParent(PaneType.MAIN_FRAME_PANE, false)
    }

    @Override
    void stop() throws Exception {
        springContext.stop()
    }
}
