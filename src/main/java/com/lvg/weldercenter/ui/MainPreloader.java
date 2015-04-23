package com.lvg.weldercenter.ui;

import com.lvg.weldercenter.ui.control.PreloadPaneController;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Victor on 21.04.2015.
 */
public class MainPreloader extends Preloader {

    private final FXMLLoader PRELOADER_PANE_LOADER = new FXMLLoader(getClass().getResource("/fxml/preload-pane.fxml"));

    private ProgressBar progressBar;
    private Stage stage;
    private boolean noLoadingProgress = true;
    private PreloadPaneController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setScene(createPreloaderScene());
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    private Scene createPreloaderScene()throws Exception{

        BorderPane pane = (BorderPane)PRELOADER_PANE_LOADER.load();
        controller = PRELOADER_PANE_LOADER.getController();
        progressBar = controller.getProgressBar();
        return new Scene(pane);
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        //application loading progress is rescaled to be first 50%
        //Even if there is nothing to load 0% and 100% events can be
        // delivered
        if (pn.getProgress() != 1.0 || !noLoadingProgress) {
            progressBar.setProgress(pn.getProgress()/2);
            if (pn.getProgress() > 0) {
                noLoadingProgress = false;
            }
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {

//        if (info.getType() == StateChangeNotification.Type.BEFORE_START){
//           stage.hide();
//        }
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification pn) {
        if (pn instanceof ProgressNotification) {
            //expect application to send us progress notifications
            //with progress ranging from 0 to 1.0
            double v = ((ProgressNotification) pn).getProgress();
            if (!noLoadingProgress) {
                //if we were receiving loading progress notifications
                //then progress is already at 50%.
                //Rescale application progress to start from 50%
                v = 0.5 + v/2;
            }
            progressBar.setProgress(v);
        } else if (pn instanceof StateChangeNotification) {
            //hide after get any state update from application
            stage.hide();
        }
    }
}
