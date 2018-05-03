package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.repositories.QualificationDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllQualificationsService
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadAllQualificationsChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(LoadAllQualificationsChangeStateListener.class)

    @Autowired
    LoadingAllQualificationsService service

    @Autowired
    QualificationDTORepository qualificationRepository

    @Override
    void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
        if (newValue == Worker.State.FAILED){
            service.stateProperty().removeListener(this)
        }
        if (newValue == Worker.State.SUCCEEDED){
            LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
            def list = service.getValue()
            LOGGER.debug("Qualification list was updated - list: $list")
            qualificationRepository.updateQualificationDTOList(list)
            service.stateProperty().removeListener(this)
            LOGGER.debug("Qualification list was updated")
            LOGGER.debug("-----LISTENER-END----")
        }else {
            LOGGER.debug("State value is ${newValue}")
        }
    }
}
