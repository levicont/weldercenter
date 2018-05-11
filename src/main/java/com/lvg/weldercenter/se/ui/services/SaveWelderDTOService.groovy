package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.tasks.welders.SaveWelderTask
import javafx.concurrent.Task
import org.springframework.stereotype.Component

@Component
class SaveWelderDTOService extends ParameterUIService<WelderDTO, WelderDTO>{

    @Override
    protected Task<WelderDTO> getTask() {
        return ctx.getBean(SaveWelderTask.class, parameter)
    }

}
