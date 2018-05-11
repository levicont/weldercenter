package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.tasks.welders.AllWeldersTableViewTask
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.stereotype.Component

@Component
class LoadingWeldersForTableViewService extends UIService<ObservableList<WelderTableViewDTO>>{

    @Override
    protected Task<ObservableList<WelderTableViewDTO>> getTask() {
        return ctx.getBean(AllWeldersTableViewTask.class)
    }
}
