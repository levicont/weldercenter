package com.lvg.weldercenter.ui;

import com.lvg.weldercenter.models.Job;
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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
        Parent root  = FXMLLoader.load(getClass().getResource("/fxml/javafx-ui.fxml"));
        stage.setTitle("Welder center");
        stage.setScene(new Scene(root, Color.LIGHTBLUE));


        stage.show();
        System.out.println(" **** end of start() method");
    }

    @Override
    public void init() throws Exception {
        super.init();


    }
}
