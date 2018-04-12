package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.tasks.qualification.QualificationsTask
import javafx.collections.ObservableList
import javafx.concurrent.Service
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

@Component
class LoadingAllQualificationsService extends Service<ObservableList<String>> implements OnceStartedService{

    @Autowired
    ConfigurableApplicationContext ctx


    private boolean startedOnce = false

    @Override
    boolean isStartedOnce() {
        return startedOnce
    }

    @Override
    void setStartedOnceFlag(boolean startedOnce) {
        this.startedOnce = startedOnce
    }

    @Override
    protected Task<ObservableList<String>> createTask() {
        return ctx.getBean(QualificationsTask.class)
    }
}
