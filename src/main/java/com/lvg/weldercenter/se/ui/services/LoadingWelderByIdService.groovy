package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.tasks.welders.GetWelderByIdTask
import javafx.concurrent.Task
import org.springframework.stereotype.Component

@Component
class LoadingWelderByIdService extends ParameterUIService<Long, WelderDTO>{

    @Override
    protected Task<WelderDTO> getTask() {
        return ctx.getBean(GetWelderByIdTask.class, parameter)
    }
}
