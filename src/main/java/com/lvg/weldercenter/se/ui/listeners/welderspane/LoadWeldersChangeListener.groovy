package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.services.LoadingWeldersService
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadWeldersChangeListener extends GenericServiceChangeStateListener {
    private static final Logger LOGGER = Logger.getLogger(LoadWeldersChangeListener.class)

    @Autowired
    LoadingWeldersService service

    @Override
    void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
        if (loadingView == null)
            loadingView = mainFrameController.getLoadingView(service)

        if (newValue == Worker.State.FAILED){
            service.stateProperty().removeListener(this)
        }
        if (newValue == Worker.State.SUCCEEDED){
            LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
            def list = service.getValue()
            LOGGER.debug("Welders list was updated - list: $list")
            //TODO Here have to be updating weldersList code
            service.stateProperty().removeListener(this)
            loadingView.hide()
            LOGGER.debug("Welders list was updated")
            LOGGER.debug("-----LISTENER-END----")
        }
        if (isShowingState(newValue)){
            loadingView.show()
        }
    }


}
