package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import com.lvg.weldercenter.se.ui.tasks.welders.AllWeldersTableViewTask
import javafx.collections.ObservableList
import javafx.concurrent.Service
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

@Component
class LoadingWeldersForTableViewService extends Service<ObservableList<WelderTableViewDTO>> implements OnceStartedService{
    @Autowired
    ConfigurableApplicationContext ctx

    private boolean onceStarted = false

    @Override
    protected Task<ObservableList<WelderTableViewDTO>> createTask() {
        return ctx.getBean(AllWeldersTableViewTask.class)
    }

    @Override
    boolean isStartedOnce() {
        return onceStarted
    }

    @Override
    void setStartedOnceFlag(boolean startedOnce) {
        onceStarted = startedOnce
    }
}
