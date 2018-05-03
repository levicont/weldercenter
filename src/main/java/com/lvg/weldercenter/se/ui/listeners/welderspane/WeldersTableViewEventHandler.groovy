package com.lvg.weldercenter.se.ui.listeners.welderspane

import com.lvg.weldercenter.se.ui.controllers.WelderController
import com.lvg.weldercenter.se.ui.controllers.WelderTableController
import com.lvg.weldercenter.se.ui.dto.DTOConstants
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.services.LoadingWelderByIdService
import com.lvg.weldercenter.se.ui.utils.ControlFXUtils
import com.lvg.weldercenter.se.ui.utils.Printer
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import javafx.event.Event
import javafx.event.EventHandler
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WeldersTableViewEventHandler implements EventHandler<Event> {
    private static final Logger LOGGER = Logger.getLogger(WeldersTableViewEventHandler.class)

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
        LOGGER.debug("--- BEGIN doSelectWelder ---")
        WelderTableViewDTO selectedWelder = welderTableController.getWeldersTableView()
                .selectionModel.getSelectedItem()
        if (selectedWelder == null) {
            welderController.welderDTOProperty().set(null)
            LOGGER.debug("selectedWelder is null")
            return
        }
//        unbind(selectedWelder)
        if (selectedWelder.id == DTOConstants.NULL_ID_FIELD_DEFAULT)  {
            welderController.loadNewWelder()
            LOGGER.debug("selectedWelder is unsaved it has id: ${selectedWelder.id}")
            Printer.logDTO(WelderTableViewDTO.class, selectedWelder)
            return
        }
        //If we have an empty welder then we are deleting it from TableView
        welderController.welderDTORepository.removeUnsavedItems()
        LOGGER.debug("selectedWelder is presented in DB ")
        Printer.logDTO(WelderTableViewDTO.class, selectedWelder)
        LOGGER.debug("Try to start loadingWelderByIdService")
        loadingWelderByIdService.setId(selectedWelder.id)
        loadingWelderByIdService.stateProperty().addListener(listener)
        ServiceUtils.startService(loadingWelderByIdService)

    }

}
