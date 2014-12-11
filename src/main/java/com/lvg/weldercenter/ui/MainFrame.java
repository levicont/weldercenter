package com.lvg.weldercenter.ui;

import com.lvg.weldercenter.models.Job;
import com.lvg.weldercenter.ui.control.MainFrameController;
import impl.org.controlsfx.i18n.Localization;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/javafx-ui.fxml"));

        Parent root  = (Parent)loader.load();
        Localization.setLocale(new Locale("ru","RU"));
        stage.setTitle("Welder center");
        stage.setScene(new Scene(root, Color.LIGHTBLUE));
        MainFrameController controller = (MainFrameController)loader.getController();
        controller.setStageAndListeners(stage);
        stage.show();
        System.out.println(" **** end of start() method");
    }

    @Override
    public void init() throws Exception {
        super.init();


    }
}
