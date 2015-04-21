package com.lvg.weldercenter.ui.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Victor on 21.04.2015.
 */
public class PreloadPaneController implements Initializable {

    @FXML
    BorderPane mainPane;
    @FXML
    ProgressBar progressBar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
