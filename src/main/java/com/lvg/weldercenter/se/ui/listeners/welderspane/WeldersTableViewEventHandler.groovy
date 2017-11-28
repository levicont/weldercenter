package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.controllers.WelderTableController
import com.lvg.weldercenter.se.ui.services.LoadingWelderByIdService
import com.lvg.weldercenter.se.ui.utils.ControlFXUtils
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import javafx.event.Event
import javafx.event.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WeldersTableViewEventHandler implements EventHandler<Event> {

    @Autowired
    LoadingWelderByIdService loadingWelderByIdService

    @Autowired
    LoadWelderByIdChangeStateListener listener

    @Autowired
    WelderTableController welderTableController

    @Override
    void handle(Event event) {
        if (ControlFXUtils.isEventIsSelectedKeyOnList(event) ||
                ControlFXUtils.isEventIsSelectedMouse(event)) {
            doSelectWelder()
        }
    }

    private void doSelectWelder() {
        if (welderTableController.getWeldersTableView().selectionModel.getSelectedItem() != null) {
            loadingWelderByIdService.stateProperty().addListener(listener)
            ServiceUtils.startService(loadingWelderByIdService)
        }
    }


}
