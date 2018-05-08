package com.lvg.weldercenter.se.ui.tasks.welders

import com.lvg.weldercenter.se.exceptions.WelderCenterException
import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.services.WelderService
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.tasks.TaskConstants
import javafx.application.Platform
import javafx.concurrent.Task
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope('prototype')
class SaveWelderTask extends Task<WelderDTO> implements TaskConstants{
    private static final Logger LOGGER = Logger.getLogger(SaveWelderTask.class)

    @Autowired
    WelderService welderService

    SaveWelderTask(WelderDTO welderDTO){
        super()
        this.welderDTO = welderDTO
    }

    private final WelderDTO welderDTO


    @Override
    protected WelderDTO call() throws Exception {
        try {
            updateTitle(SAVE_WELDER_DTO_TASK_TITLE_MESSAGE)
            final Welder welder  = welderService.save(welderDTO.getWelder())
            LOGGER.debug("WelderDTO with id: ${welder.id} has saved in DB")
            updateMessage("WelderDTO with id: ${welder.id} has saved in DB")
            Platform.runLater({
                welderDTO.updateWelderDTO(welder)
            })
            return welderDTO
        }catch(Exception ex){
            LOGGER.warn("There is exception during saving welder: ${welderDTO} ex: ${ex.getMessage()}" )
            throw new WelderCenterException(ex.getMessage())
        }


    }

    @Override
    protected void succeeded() {
        super.succeeded()
        updateMessage(TASK_SUCCESS_MESSAGE)
    }

    @Override
    protected void cancelled() {
        super.cancelled()
        updateMessage(TASK_CANCELED_MESSAGE)
    }

    @Override
    protected void failed() {
        super.failed()
        updateMessage(TASK_FAIL_MESSAGE)
    }
}
