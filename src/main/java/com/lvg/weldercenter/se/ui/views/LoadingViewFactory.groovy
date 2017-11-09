package com.lvg.weldercenter.se.ui.views

import javafx.concurrent.Worker
import javafx.stage.Window
import org.springframework.stereotype.Component

@Component
class LoadingViewFactory {

    private static LoadingView loadingView

    private LoadingViewFactory(){}

    static LoadingView getLoadingView(Window owner, Worker worker){
        if(loadingView == null){
            loadingView = new LoadingView(owner, worker)
            return loadingView
        }
        loadingView.initUI(owner)
        loadingView.bindToWorker(worker)
        return loadingView
    }
}
