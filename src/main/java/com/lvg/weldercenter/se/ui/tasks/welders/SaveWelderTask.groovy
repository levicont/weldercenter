package com.lvg.weldercenter.se.ui.tasks.welders

import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.services.WelderService
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.tasks.TaskConstants
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope('prototype')
class SaveWelderTask extends Task<WelderDTO> implements TaskConstants{

    @Autowired
    WelderService welderService

    SaveWelderTask(WelderDTO welderDTO){
        super()
        this.welderDTO = welderDTO
    }

    private WelderDTO welderDTO


    @Override
    protected WelderDTO call() throws Exception {
        updateTitle(SAVE_WELDER_DTO_TASK_TITLE_MESSAGE)
        Welder welder  = welderService.save(welderDTO.getWelder())
        updateMessage("WelderDTO with id: ${welder.id} has saved in DB")
        WelderDTO result = new WelderDTO(welder)
        updateValue(result)
        return result
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
