package com.lvg.weldercenter.se.ui.tasks.welders

import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.services.OrganizationService
import com.lvg.weldercenter.se.services.WelderService
import com.lvg.weldercenter.se.ui.controllers.WelderTableController
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.dto.WelderUI
import javafx.concurrent.Task
import org.apache.log4j.Logger
import org.hibernate.Hibernate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Scope('prototype')
class GetWelderByIdTask extends Task<WelderUI>{
    private static final Logger LOGGER = Logger.getLogger(GetWelderByIdTask.class)
    private static final String GET_WELDER_BY_ID_TASK_TITLE_MESSAGE = 'Получение сварщика из БД...'

    @Autowired
    WelderService welderService
    @Autowired
    OrganizationService organizationService

    @Autowired
    WelderTableController welderTableController


    @Override
    protected WelderUI call() throws Exception {
        LOGGER.debug("---TASK-STARTED---${getClass().getSimpleName()}")
        WelderTableViewDTO welderDTO = welderTableController.getWeldersTableView().getSelectionModel().getSelectedItem()
        if (welderDTO == null){
            LOGGER.warn('No one welder is selected')
            //TODO fix problem
            cancel()
        }


        updateTitle(GET_WELDER_BY_ID_TASK_TITLE_MESSAGE)
        Welder welder = welderService.getFull(welderDTO.getId())
        //welder.organization = organizationService.get(welder.organization.id)
        LOGGER.debug("---WELDER-FOUND: ${welder}")
        LOGGER.debug("---WELDER-HAS-ORGANIZATION: ${welder.organization}")
        WelderUI result = new WelderUI(welder)
        LOGGER.debug("---WELDER_UI-RECEIVED: ${result}")
        updateValue(result)

        try {
            Thread.sleep(500)
        }catch (InterruptedException ex){
            ex.printStackTrace()
        }
        LOGGER.debug("---TASK-ENDED---${getClass().getSimpleName()}")
        return result
    }

    @Override
    protected void succeeded() {
        super.succeeded()
        updateMessage(TaskConstants.TASK_SUCCESS_MESSAGE)
    }

    @Override
    protected void cancelled() {
        super.cancelled()
        updateMessage(TaskConstants.TASK_CANCELED_MESSAGE)
    }

    @Override
    protected void failed() {
        super.failed()
        updateMessage(TaskConstants.TASK_FAIL_MESSAGE)
    }
}
