package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.EducationDTO
import com.lvg.weldercenter.se.ui.tasks.education.EducationsTask
import javafx.collections.ObservableList
import javafx.concurrent.Service
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

@Component
class LoadingAllEducationsService extends Service<ObservableList<EducationDTO>> implements OnceStartedFlag{

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
    protected Task<ObservableList<EducationDTO>> createTask() {
        return ctx.getBean(EducationsTask.class)
    }
}
