package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.repositories.JobDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllJobsService
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadAllJobChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(LoadAllJobChangeStateListener.class)

    @Autowired
    LoadingAllJobsService service

    @Autowired
    JobDTORepository jobDTORepository


    @Override
    void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
        if (newValue == Worker.State.FAILED){
            service.stateProperty().removeListener(this)
        }
        if (newValue == Worker.State.SUCCEEDED){
            LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
            def list = service.getValue()
            LOGGER.debug("Job list was updated - list: $list")
            jobDTORepository.refreshAllDTO(list)
            service.stateProperty().removeListener(this)
            LOGGER.debug("Job list was updated")
            LOGGER.debug("-----LISTENER-END----")
        }else {
            LOGGER.debug("State value is ${newValue}")
        }
    }
}
