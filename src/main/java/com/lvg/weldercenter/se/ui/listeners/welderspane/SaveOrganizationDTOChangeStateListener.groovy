package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.services.SaveOrganizationDTOService
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SaveOrganizationDTOChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(SaveWelderDTOChangeStateListener.class)

    @Autowired
    SaveOrganizationDTOChangeStateListener(SaveOrganizationDTOService service) {
        this.service = service
        this.needToShowLoadingView = true
    }

    @Override
    protected void doWhenSucceeded() {
        LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
        OrganizationDTO organizationDTO = (OrganizationDTO)service.getValue()
        LOGGER.debug("Organization has saved: $organizationDTO")
        //TODO organization list must be updated
        LOGGER.debug("-----LISTENER-END----")
    }

}
