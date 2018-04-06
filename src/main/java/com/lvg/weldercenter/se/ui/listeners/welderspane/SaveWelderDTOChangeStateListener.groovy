package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.controllers.WelderController
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.services.SaveWelderDTOService
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SaveWelderDTOChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(SaveWelderDTOChangeStateListener.class)

    @Autowired
    SaveWelderDTOService saveWelderDTOService

    @Autowired
    WelderController welderController

    @Override
    void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
        if (loadingView == null)
            loadingView = mainFrameController.getLoadingView(saveWelderDTOService)
        LOGGER.debug("---- SaveWelderDTO LISTENER Service state: ${newValue} ----")
        if (newValue == Worker.State.SUCCEEDED){
            LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
            WelderDTO welderDTO = saveWelderDTOService.getValue()
            LOGGER.debug("Welder has saved: $welderDTO")
            //welderController.loadWelder(welderDTO)
            saveWelderDTOService.stateProperty().removeListener(this)
            loadingView.hide()
            LOGGER.debug("-----LISTENER-END----")
        }
        if (isShowingState(newValue)){
            loadingView.show()
        }
    }
}
