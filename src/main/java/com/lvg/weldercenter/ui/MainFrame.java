package com.lvg.weldercenter.ui;

import com.lvg.weldercenter.spring.ContextFactory;
import com.lvg.weldercenter.ui.control.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import javax.swing.*;


/**
 * Created by Victor Levchenko LVG Corp. on 24.10.14.
 */
public class MainFrame extends Application {
    private static final Logger LOGGER = Logger.getLogger("MainFrame.class");
    private final String APPLICATION_TITLE = "Аттестационный центр сварщиков";
    private final String LOGO_ICON_PATH = "img/logo64x64.png";
    private ControllerManager controllerManager;
    private Stage stage;
    private JProgressBar progressBar;
    private SplashSwingFrame splashWindow;


    @Override
    public void start(final Stage stage) throws Exception {
        LOGGER.info(" **** in start() method");
        progressBar.setValue(30);
        this.stage = stage;
        controllerManager = new ControllerManager(stage);
        progressBar.setValue(55);
        splashWindow.getLbLoadingModuleName().setText(" Инициализация главного модуля");
        stage.setTitle(APPLICATION_TITLE);
        stage.getIcons().add(new Image(ClassLoader.getSystemClassLoader().getResourceAsStream(LOGO_ICON_PATH)));
        stage.setScene(new Scene(controllerManager.getMainFrame(), Color.LIGHTBLUE));
        initControllers(controllerManager);
        progressBar.setValue(100);
        splashWindow.dispose();
        stage.show();
    }

    @Override
    public void init() throws Exception {
       splashWindow = (SplashSwingFrame)ContextFactory.getApplicationContext().getBean("splashSwingFrame");
       progressBar = splashWindow.getProgressBar();
    }

    private void initControllers(ControllerManager controllerManager){

        MainFrameController mainFrameController = controllerManager.getMainFrameController();
        mainFrameController.setControllerManager(controllerManager);
        mainFrameController.setStageAndListeners(controllerManager.getStage());

        splashWindow.getLbLoadingModuleName().setText(" Инициализация настроек приложения");
        progressBar.setValue(75);
        PropertiesController propertiesController = controllerManager.getPropertiesController();
        propertiesController.setControllerManager(controllerManager);


        splashWindow.getLbLoadingModuleName().setText(" Инициализация модуля сварщиков");
        progressBar.setValue(80);
        WelderController welderController = controllerManager.getWelderController();
        welderController.setControllerManager(controllerManager);

        splashWindow.getLbLoadingModuleName().setText(" Инициализация модуля журналов");
        progressBar.setValue(85);
        JournalController journalController = controllerManager.getJournalController();
        journalController.setControllerManager(controllerManager);

        splashWindow.getLbLoadingModuleName().setText(" Инициализация модуля протоколов");
        progressBar.setValue(90);
        ProtocolController protocolController = controllerManager.getProtocolController();
        protocolController.setControllerManager(controllerManager);

        splashWindow.getLbLoadingModuleName().setText(" Инициализация модуля отчетов");
        progressBar.setValue(95);
        ReportViewController reportViewController = controllerManager.getReportViewController();
        reportViewController.setControllerManager(controllerManager);


    }
}
