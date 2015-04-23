package com.lvg.weldercenter.ui;

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

import java.util.Locale;
import java.util.logging.Logger;

/**
 * Created by Victor Levchenko LVG Corp. on 24.10.14.
 */
public class MainFrame extends Application {
    private static final Logger LOGGER = Logger.getLogger("MainFrame.class");
    private ControllerManager controllerManager;
    private Stage stage;
    private BooleanProperty ready = new SimpleBooleanProperty(false);

    private void longStart() {
        //simulate long init in background
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int max = 20;
                for (int i = 1; i <= max; i++) {
                    Thread.sleep(500);
                    // Send progress to preloader
                    notifyPreloader(new Preloader.ProgressNotification(((double) i)/max));
                }
                // After init is ready, the app is ready to be shown
                // Do this before hiding the preloader stage to prevent the
                // app from exiting prematurely
                ready.setValue(Boolean.TRUE);

                notifyPreloader(new Preloader.StateChangeNotification(
                        Preloader.StateChangeNotification.Type.BEFORE_START));

                return null;
            }
        };
        new Thread(task).start();
    }


    @Override
    public void start(final Stage stage) throws Exception {
        LOGGER.info(" **** in start() method");
        longStart();
        this.stage = stage;
        System.out.println(" **** in start() method");
        controllerManager = new ControllerManager(stage);
        Localization.setLocale(new Locale("ru","RU"));
        stage.setTitle("Welder center");
        stage.setScene(new Scene(controllerManager.getMainFrame(), Color.LIGHTBLUE));
        initControllers(controllerManager);

        // After the app is ready, show the stage
        ready.addListener(new ChangeListener<Boolean>(){
            public void changed(
                    ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (Boolean.TRUE.equals(t1)) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            stage.show();
                        }
                    });
                }
            }
        });


        //stage.show();


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
