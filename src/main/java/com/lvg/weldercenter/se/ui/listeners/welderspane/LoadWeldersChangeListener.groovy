package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.services.LoadingWeldersService
import javafx.collections.ObservableList
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadWeldersChangeListener extends GenericServiceChangeStateListener {
    private static final Logger LOGGER = Logger.getLogger(LoadWeldersChangeListener.class)

    @Autowired
    LoadWeldersChangeListener(LoadingWeldersService service) {
        this.service = service
        this.needToShowLoadingView = true
    }

    @Override
    void doWhenSucceeded() {
        LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
        ObservableList<WelderDTO> list = (ObservableList<WelderDTO>)service.getValue()
        LOGGER.debug("Welders list was updated - list: $list")
        //TODO Here have to be updating weldersList code
        LOGGER.debug("Welders list was updated")
        LOGGER.debug("-----LISTENER-END----")
    }


}
