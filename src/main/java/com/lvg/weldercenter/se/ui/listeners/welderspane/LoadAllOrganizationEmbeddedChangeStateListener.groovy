package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.models.OrganizationEmbedded
import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.repositories.OrganizationDTORepository
import com.lvg.weldercenter.se.ui.repositories.WelderDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllOrganizationEmbeddedService
import com.lvg.weldercenter.se.ui.services.LoadingAllOrganizationsService
import javafx.collections.ObservableList
import javafx.collections.ObservableSet
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by Victor Levchenko LVG Corp. on 15.12.2019.
 */
@Component
class LoadAllOrganizationEmbeddedChangeStateListener extends GenericServiceChangeStateListener {

    private static final Logger LOGGER = Logger.getLogger(LoadAllOrganizationEmbeddedChangeStateListener.class)

    @Autowired
    WelderDTORepository repository

    @Autowired
    LoadAllOrganizationEmbeddedChangeStateListener(LoadingAllOrganizationEmbeddedService service) {
        this.service = service
    }

    @Override
    void doWhenSucceeded() {
        LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
        ObservableSet<OrganizationEmbedded> organizationsSet = (ObservableSet<OrganizationEmbedded>)service.getValue()
        LOGGER.debug("Organization set was updated - set: $organizationsSet")
        repository.updateOrganizationEmbeddedSet(organizationsSet)
        LOGGER.debug("Organization list was updated")
        LOGGER.debug("-----LISTENER-END----")
    }

}
