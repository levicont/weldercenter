package com.lvg.weldercenter.ui;

import com.lvg.weldercenter.spring.ContextFactory;
import com.lvg.weldercenter.ui.control.*;
import impl.org.controlsfx.i18n.Localization;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by Victor Levchenko LVG Corp. on 24.10.14.
 */
public class MainFrame extends Application {
    private static final Logger LOGGER = Logger.getLogger("MainFrame.class");
    private ControllerManager controllerManager;
    private Stage stage;
    private JProgressBar progressBar;
    private SplashSwingFrame splashWindow;


    @Override
    public void start(final Stage stage) throws Exception {
        LOGGER.info(" **** in start() method");
        progressBar.setValue(30);
        this.stage = stage;
        System.out.println(" **** in start() method");
        controllerManager = new ControllerManager(stage);
        progressBar.setValue(75);
        Localization.setLocale(new Locale("ru","RU"));
        stage.setTitle("Welder center");
        stage.setScene(new Scene(controllerManager.getMainFrame(), Color.LIGHTBLUE));
        initControllers(controllerManager);
        progressBar.setValue(100);
        splashWindow.dispose();
        stage.show();


        System.out.println(" **** end of start() method");
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

        WelderController welderController = controllerManager.getWelderController();
        welderController.setControllerManager(controllerManager);

        JournalController journalController = controllerManager.getJournalController();
        journalController.setControllerManager(controllerManager);

        ProtocolController protocolController = controllerManager.getProtocolController();
        protocolController.setControllerManager(controllerManager);
    }
}
