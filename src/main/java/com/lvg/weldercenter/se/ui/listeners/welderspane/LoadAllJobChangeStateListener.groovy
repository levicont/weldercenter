package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.dto.JobDTO
import com.lvg.weldercenter.se.ui.repositories.JobDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllJobsService
import javafx.collections.ObservableList
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadAllJobChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(LoadAllJobChangeStateListener.class)

    @Autowired
    JobDTORepository jobDTORepository

    @Autowired
    LoadAllJobChangeStateListener(LoadingAllJobsService service) {
        this.service = service
    }

    @Override
    void doWhenSucceeded() {
        LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
        ObservableList<JobDTO> list = (ObservableList<JobDTO>)service.getValue()
        LOGGER.debug("Job list was updated - list: $list")
        jobDTORepository.refreshAllDTO(list)
        LOGGER.debug("Job list was updated")
        LOGGER.debug("-----LISTENER-END----")
    }

}
