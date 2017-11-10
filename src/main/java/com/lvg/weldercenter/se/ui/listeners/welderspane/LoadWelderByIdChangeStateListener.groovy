package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.controllers.WelderController
import com.lvg.weldercenter.se.ui.services.LoadingWelderByIdService
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadWelderByIdChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(LoadWelderByIdChangeStateListener.class)

    @Autowired
    LoadingWelderByIdService loadingWelderByIdService

    @Autowired
    WelderController welderController

    @Override
    void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
        if (loadingView == null)
            loadingView = mainFrameController.getLoadingView(loadingWelderByIdService)

        if (newValue == Worker.State.SUCCEEDED){
            LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
            def welderUI = loadingWelderByIdService.getValue()
            LOGGER.debug("Welder found: $welderUI")
            welderController.loadWelder(welderUI)
            loadingWelderByIdService.stateProperty().removeListener(this)
            loadingView.hide()
            LOGGER.debug("Welders list was updated")
            LOGGER.debug("-----LISTENER-END----")
        }
        if (isShowingState(newValue)){
            loadingView.show()
        }
    }
}
