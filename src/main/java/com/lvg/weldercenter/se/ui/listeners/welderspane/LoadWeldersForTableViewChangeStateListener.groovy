package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.services.LoadingWeldersForTableViewService
import javafx.application.Platform
import javafx.collections.ObservableList
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadWeldersForTableViewChangeStateListener extends GenericServiceChangeStateListener{
    private static final Logger LOGGER = Logger.getLogger(LoadWeldersForTableViewChangeStateListener.class)

    @Autowired
    LoadWeldersForTableViewChangeStateListener(LoadingWeldersForTableViewService service) {
        this.service = service
        this.needToShowLoadingView = true
    }

    @Override
    protected void doWhenSucceeded() {
        LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
        ObservableList<WelderTableViewDTO> list = (ObservableList<WelderTableViewDTO>)service.getValue()
        LOGGER.debug("Welders list was updated - list: $list")
        Platform.runLater({weldersRepository.updateWeldersListForTableView(list)})
        LOGGER.debug("Welders list was updated")
        LOGGER.debug("-----LISTENER-END----"+getClass().simpleName)
    }

}
