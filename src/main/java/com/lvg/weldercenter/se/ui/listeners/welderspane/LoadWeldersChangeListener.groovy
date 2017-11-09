package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.repositories.WeldersRepository
import com.lvg.weldercenter.se.ui.services.LoadingWeldersService
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.concurrent.Worker
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoadWeldersChangeListener implements ChangeListener<Worker.State>{
    private static final Logger LOGGER = Logger.getLogger(LoadWeldersChangeListener.class)

    @Autowired
    LoadingWeldersService loadingWeldersService

    @Autowired
    WeldersRepository weldersRepository


    @Override
    void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {

        if (newValue == Worker.State.SUCCEEDED){
            LOGGER.debug("-----LISTENER-START----"+getClass().simpleName)
            def list = loadingWeldersService.getValue()
            LOGGER.debug("Welders list was updated - list: $list")
            weldersRepository.updateWeldersList(list)
            loadingWeldersService.stateProperty().removeListener(this)
            LOGGER.debug("Welders list was updated")
            LOGGER.debug("-----LISTENER-END----")
        }
    }
}
