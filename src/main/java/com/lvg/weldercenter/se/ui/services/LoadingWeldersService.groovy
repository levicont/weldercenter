package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.WelderUI
import com.lvg.weldercenter.se.ui.tasks.welders.AllWelderTask
import javafx.collections.ObservableList
import javafx.concurrent.Service
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

@Component
class LoadingWeldersService extends Service<ObservableList<WelderUI>> implements OnceStartedFlag{

    @Autowired
    ConfigurableApplicationContext ctx

    private boolean onceStarted = false

    @Override
    protected Task<ObservableList<WelderUI>> createTask() {
        return ctx.getBean(AllWelderTask.class)
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
