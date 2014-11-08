package com.lvg.weldercenter.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by Victor Levchenko LVG Corp. on 24.10.14.
 */
public class MainFrame extends Application {
    private static final Logger LOGGER = Logger.getLogger("MainFrame.class");
    @Override
    public void start(Stage stage) throws Exception {
        LOGGER.info(" **** in start() method");
        System.out.println(" **** in start() method");
        Parent root  = FXMLLoader.load(getClass().getResource("/javafx-ui.fxml"));
        stage.setTitle("Welder center");
        stage.setScene(new Scene(root, Color.LIGHTGREEN));
        stage.show();
        System.out.println(" **** end of start() method");
    }

    @Override
    public void init() throws Exception {
        super.init();

    }
}
