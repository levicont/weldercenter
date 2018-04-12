package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.exceptions.WelderCenterException
import com.lvg.weldercenter.se.ui.dto.OrganizationDTO
import com.lvg.weldercenter.se.ui.services.SaveOrganizatioDTOService
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SaveOrganizationDTOChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(SaveWelderDTOChangeStateListener.class)

    @Autowired
    SaveOrganizatioDTOService saveOrganizationDTOService

    @Override
    void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {

        if (loadingView == null)
            loadingView = mainFrameController.getLoadingView(saveOrganizationDTOService)
        LOGGER.debug("---- SaveOrganizationDTO LISTENER Service state: ${newValue} ----")
        if(newValue == Worker.State.FAILED){
            loadingView.hide()
            throw new WelderCenterException("Saving organization process is fail")
        }

        if (newValue == Worker.State.SUCCEEDED){
            LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
            OrganizationDTO organizationDTO = saveOrganizationDTOService.getValue()
            LOGGER.debug("Organization has saved: $organizationDTO")
            saveOrganizationDTOService.stateProperty().removeListener(this)
            loadingView.hide()
            LOGGER.debug("-----LISTENER-END----")
        }
        if (isShowingState(newValue)){
            loadingView.show()
        }
    }
}
