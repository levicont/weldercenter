package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.services.LoadingWeldersForTableViewService
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadWeldersForTableViewChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(LoadWeldersForTableViewChangeStateListener.class)

    @Autowired
    LoadingWeldersForTableViewService loadingService

    @Override
    void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
        if (loadingView == null)
            loadingView = mainFrameController.getLoadingView(loadingService)

        if (newValue == Worker.State.SUCCEEDED){
            LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
            def list = loadingService.getValue()
            LOGGER.debug("Welders list was updated - list: $list")
            weldersRepository.updateWeldersListForTableView(list)
            loadingService.stateProperty().removeListener(this)
            loadingView.hide()
            LOGGER.debug("Welders list was updated")
            LOGGER.debug("-----LISTENER-END----"+getClass().simpleName)
        }
        if (isShowingState(newValue)){
            loadingView.show()
        }
    }
}
