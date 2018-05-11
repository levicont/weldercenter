package com.lvg.weldercenter.se.ui.services

import javafx.concurrent.Service
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext

abstract class UIService<T> extends Service<T> implements OnceStartedService{

    @Autowired
    protected ConfigurableApplicationContext ctx
    protected boolean startedOnce = false

    @Override
    protected Task<T> createTask() {
        return getTask()
    }

    abstract protected Task<T> getTask()

    @Override
    boolean isStartedOnce() {
        return startedOnce
    }

    @Override
    void setStartedOnceFlag(boolean startedOnce) {
        this.startedOnce = startedOnce
    }
}
