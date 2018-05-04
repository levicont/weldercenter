package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.repositories.OrganizationDTORepository
import com.lvg.weldercenter.se.ui.repositories.WelderDTORepository
import com.lvg.weldercenter.se.ui.services.SaveWelderDTOService
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SaveWelderDTOChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(SaveWelderDTOChangeStateListener.class)

    @Autowired
    WelderDTORepository welderDTORepository

    @Autowired
    OrganizationDTORepository organizationDTORepository

    @Autowired
    SaveWelderDTOChangeStateListener(SaveWelderDTOService service) {
        this.service = service
        this.needToShowLoadingView = true
    }

    @Override
    protected void doWhenSucceeded() {
        LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
        WelderDTO welderDTO = (WelderDTO)service.getValue()
        LOGGER.debug("Welder has saved: $welderDTO")
        welderDTORepository.reloadWelders()
        organizationDTORepository.loadAllDTO()
        LOGGER.debug("-----LISTENER-END----")
    }

}
