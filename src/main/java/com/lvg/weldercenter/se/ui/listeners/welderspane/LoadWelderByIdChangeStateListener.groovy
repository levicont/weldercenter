package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.controllers.WelderController
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.services.LoadingWelderByIdService
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadWelderByIdChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(LoadWelderByIdChangeStateListener.class)

    @Autowired
    WelderController welderController

    @Autowired
    LoadWelderByIdChangeStateListener(LoadingWelderByIdService service) {
        this.service = service
        this.needToShowLoadingView = true
    }

    @Override
    void doWhenSucceeded() {
        LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
        WelderDTO welderDTO = (WelderDTO)service.getValue()
        if (welderDTO != null){
            LOGGER.debug("Welder found: $welderDTO")
            LOGGER.debug("Welder has organization: ${welderDTO.getOrganizationDTO()}")
            welderController.loadWelder()
            LOGGER.debug("Welders list was updated")
        }
        LOGGER.debug("-----LISTENER-END----")
    }

}
