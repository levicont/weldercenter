package com.lvg.weldercenter.se.ui.views

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.ProgressBar
import javafx.scene.layout.VBox
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.stage.Window
import org.apache.log4j.Logger

class LoadingView extends Stage {

    private static final Logger LOGGER = Logger.getLogger(LoadingView.class)
    private Window owner
    private VBox root = new VBox(8)
    private Label lbLoadingMessage = new Label('Loading...')
    private ProgressBar progressBar = new ProgressBar(-1)
    private boolean initializedOnce = false


    LoadingView(Window owner, Worker worker) {
        this.owner = owner
        addUI()
        bindElements(worker)
    }

    void bindToWorker(Worker worker) {
        bindElements(worker)
    }

    void initUI(Window window) {
        this.owner = window
        addUI()
    }

    private void addUI() {
        if (!initializedOnce) {
            root.setAlignment(Pos.CENTER)

            root.setPrefHeight(100)
            root.setPrefWidth(200)

            progressBar.setPrefHeight(8)
            progressBar.setMinHeight(8)
            progressBar.setMaxHeight(8)
            progressBar.setPrefWidth(150)
            progressBar.setMinWidth(150)
            progressBar.setMaxWidth(150)

            root.getChildren().add(0, lbLoadingMessage)
            root.getChildren().add(1, progressBar)

            initModality(Modality.WINDOW_MODAL)
            setScene(new Scene(root))
            initStyle(StageStyle.UNDECORATED)
            initOwner(owner)
            initializedOnce = true
        }



    }

    private void bindElements(Worker worker) {
        lbLoadingMessage.textProperty().bind(worker.titleProperty())
        progressBar.progressProperty().bind(worker.progressProperty())
    }
}
