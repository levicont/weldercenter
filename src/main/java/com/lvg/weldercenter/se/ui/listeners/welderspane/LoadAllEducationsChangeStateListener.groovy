package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.controllers.WelderController
import com.lvg.weldercenter.se.ui.repositories.EducationDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllEducationsService
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadAllEducationsChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(LoadAllEducationsChangeStateListener.class)

    @Autowired
    LoadingAllEducationsService loadingAllEducationsService

    @Autowired
    EducationDTORepository educationDTORepository

    @Autowired
    WelderController welderController


    @Override
    void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
        if (newValue == Worker.State.SUCCEEDED){
            LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
            def list = loadingAllEducationsService.getValue()
            LOGGER.debug("Education list was updated - list: $list")
            educationDTORepository.updateEducationDTOList(list)
            //welderController.updateEducations()
            loadingAllEducationsService.stateProperty().removeListener(this)
            LOGGER.debug("Education list was updated")
            LOGGER.debug("-----LISTENER-END----")
        }else {
            LOGGER.debug("State value is ${newValue}")
        }
    }
}
