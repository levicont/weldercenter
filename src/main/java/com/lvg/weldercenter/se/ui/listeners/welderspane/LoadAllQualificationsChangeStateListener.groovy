package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.repositories.QualificationDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllQualificationsService
import javafx.beans.value.ObservableValue
import javafx.collections.ObservableList
import javafx.concurrent.Worker
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadAllQualificationsChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(LoadAllQualificationsChangeStateListener.class)

    @Autowired
    QualificationDTORepository qualificationRepository

    @Autowired
    LoadAllQualificationsChangeStateListener(LoadingAllQualificationsService service) {
        this.service = service
    }

    @Override
    void doWhenSucceeded() {
        LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
        ObservableList<String> list = (ObservableList<String>)service.getValue()
        LOGGER.debug("Qualification list was updated - list: $list")
        qualificationRepository.updateQualificationDTOList(list)
        LOGGER.debug("Qualification list was updated")
        LOGGER.debug("-----LISTENER-END----")
    }

}
