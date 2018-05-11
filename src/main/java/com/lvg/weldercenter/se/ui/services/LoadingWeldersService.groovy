package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.tasks.welders.AllWelderTask
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.stereotype.Component

@Component
class LoadingWeldersService extends UIService<ObservableList<WelderDTO>>{

    @Override
    protected Task<ObservableList<WelderDTO>> getTask() {
        return ctx.getBean(AllWelderTask.class)
    }
}
