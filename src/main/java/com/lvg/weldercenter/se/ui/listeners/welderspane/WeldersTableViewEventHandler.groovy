package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.controllers.WelderController
import com.lvg.weldercenter.se.ui.controllers.WelderTableController
import com.lvg.weldercenter.se.ui.dto.DTOConstants
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
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

    @Autowired
    WelderController welderController

    @Override
    void handle(Event event) {
        if (ControlFXUtils.isEventIsSelectedKeyOnList(event) ||
                ControlFXUtils.isEventIsSelectedMouse(event)) {
            doSelectWelder()
        }
    }

    private void doSelectWelder() {
        WelderTableViewDTO selectedWelder = welderTableController.getWeldersTableView()
                .selectionModel.getSelectedItem()
        if (selectedWelder == null) {
            welderController.welderDTOProperty().set(null)
            return
        }
        if (selectedWelder.id == DTOConstants.NULL_ID_FIELD_DEFAULT)  {
            welderController.loadWelder(WelderDTO.defaultWelderDTO())
            return
        }
        //If we have an empty welder then we are deleting it from TableView
        welderController.welderDTORepository.clearWelderTableViewDTOList()

        loadingWelderByIdService.stateProperty().addListener(listener)
        ServiceUtils.startService(loadingWelderByIdService)
    }
}
