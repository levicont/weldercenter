package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.repositories.OrganizationDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllOrganizationsService
import javafx.collections.ObservableList
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadAllOrganizationsChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(LoadAllOrganizationsChangeStateListener.class)

    @Autowired
    OrganizationDTORepository repository

    @Autowired
    LoadAllOrganizationsChangeStateListener(LoadingAllOrganizationsService service) {
        this.service = service
    }

    @Override
    void doWhenSucceeded() {
        LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
        ObservableList<OrganizationDTO> list = (ObservableList<OrganizationDTO>)service.getValue()
        LOGGER.debug("Organization list was updated - list: $list")
        repository.refreshAllDTO(list)
        LOGGER.debug("Organization list was updated")
        LOGGER.debug("-----LISTENER-END----")
    }


}
