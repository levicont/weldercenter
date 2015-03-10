package com.lvg.weldercenter.ui;

import com.lvg.weldercenter.ui.control.*;
import impl.org.controlsfx.i18n.Localization;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by Victor Levchenko LVG Corp. on 24.10.14.
 */
public class MainFrame extends Application {
    private static final Logger LOGGER = Logger.getLogger("MainFrame.class");
    private ControllerManager controllerManager;

    @Override
    public void start(Stage stage) throws Exception {
        LOGGER.info(" **** in start() method");
        System.out.println(" **** in start() method");
        controllerManager = new ControllerManager(stage);
        Localization.setLocale(new Locale("ru","RU"));
        stage.setTitle("Welder center");
        stage.setScene(new Scene(controllerManager.getMainFrame(), Color.LIGHTBLUE));
        initControllers(controllerManager);
        stage.show();
        System.out.println(" **** end of start() method");
    }

    @Override
    public void init() throws Exception {
        super.init();
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
