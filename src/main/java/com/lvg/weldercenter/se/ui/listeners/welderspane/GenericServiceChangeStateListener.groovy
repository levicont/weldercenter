package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.controllers.MainFrameController
import com.lvg.weldercenter.se.ui.repositories.WelderDTORepository
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.concurrent.Service
import javafx.concurrent.Worker
import javafx.stage.Stage
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired

import static javafx.concurrent.Worker.State.*

abstract class GenericServiceChangeStateListener implements ChangeListener<Worker.State>{
    private static final Logger LOGGER = Logger.getLogger(GenericServiceChangeStateListener.class)
    protected boolean needToShowLoadingView = false

    @Autowired
    protected WelderDTORepository weldersRepository

    @Autowired
    protected MainFrameController mainFrameController

    protected Service service

    protected Stage loadingView

    protected static boolean isShowingState(Worker.State state){
        switch(state) {
            case SUCCEEDED: return false
            case RUNNING: return true
            case SCHEDULED: return false
            case CANCELLED: return false
            case FAILED: return false
        }
        return false
    }

    @Override
    void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
        if (loadingView == null) {
            loadingView == mainFrameController.getLoadingView(service)
        }
        if (newValue == FAILED){
            doWhenFailed()
            removeListener()
        }
        if (newValue == SUCCEEDED){
            doWhenSucceeded()
            removeListener()
        }
        if (needToShowLoadingView && isShowingState(newValue)){
            loadingView.hide()
        }
    }

    protected void doWhenFailed(){
            LOGGER.warn("${this.class.simpleName}: service FAILED")
    }


    protected abstract void doWhenSucceeded()

    protected void removeListener(){
        service.stateProperty().removeListener(this)
    }

    protected void setShowLoadingViewFlag(boolean flag){
        showLoadingViewFlag = flag
    }
}
