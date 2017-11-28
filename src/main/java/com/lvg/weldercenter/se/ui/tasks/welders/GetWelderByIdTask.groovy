package com.lvg.weldercenter.se.ui.tasks.welders

import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.services.WelderService
import com.lvg.weldercenter.se.ui.controllers.WelderTableController
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.tasks.TaskConstants
import javafx.concurrent.Task
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope('prototype')
class GetWelderByIdTask extends Task<WelderDTO>{
    private static final Logger LOGGER = Logger.getLogger(GetWelderByIdTask.class)
    private static final String GET_WELDER_BY_ID_TASK_TITLE_MESSAGE = 'Получение сварщика из БД...'

    @Autowired
    WelderService welderService

    @Autowired
    WelderTableController welderTableController


    @Override
    protected WelderDTO call() throws Exception {
        LOGGER.debug("---TASK-STARTED---${getClass().getSimpleName()}")
        WelderTableViewDTO welderDTO = welderTableController.getWeldersTableView().getSelectionModel().getSelectedItem()
        updateTitle(GET_WELDER_BY_ID_TASK_TITLE_MESSAGE)
        Welder welder = welderService.getFull(welderDTO.getId())
        LOGGER.debug("---WELDER-FOUND: ${welder}")
        LOGGER.debug("---WELDER-HAS-ORGANIZATION: ${welder.organization}")
        WelderDTO result = new WelderDTO(welder)
        LOGGER.debug("---WELDER_UI-RECEIVED: ${result}")
        updateValue(result)
        LOGGER.debug("---TASK-ENDED---${getClass().getSimpleName()}")
        return result
    }

    @Override
    protected void succeeded() {
        super.succeeded()
        updateMessage(TaskConstants.TASK_SUCCESS_MESSAGE)
        LOGGER.info(titleProperty().get())
    }

    @Override
    protected void cancelled() {
        super.cancelled()
        updateMessage(TaskConstants.TASK_CANCELED_MESSAGE)
        LOGGER.info(titleProperty().get())
    }

    @Override
    protected void failed() {
        super.failed()
        updateMessage(TaskConstants.TASK_FAIL_MESSAGE)
    }
}
