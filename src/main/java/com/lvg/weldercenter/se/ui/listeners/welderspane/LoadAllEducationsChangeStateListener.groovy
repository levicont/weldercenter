package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.repositories.EducationDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllEducationsService
import javafx.collections.ObservableList
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadAllEducationsChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(LoadAllEducationsChangeStateListener.class)

    @Autowired
    EducationDTORepository educationDTORepository

    @Autowired
    LoadAllEducationsChangeStateListener(LoadingAllEducationsService service){
        this.service = service
    }

    @Override
    void doWhenSucceeded() {
        LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
        ObservableList<String> list = (ObservableList<String>)service.getValue()
        LOGGER.debug("Education list was updated - list: $list")
        educationDTORepository.updateEducationDTOList(list)
        LOGGER.debug("Education list was updated")
        LOGGER.debug("-----LISTENER-END----")
    }

}
