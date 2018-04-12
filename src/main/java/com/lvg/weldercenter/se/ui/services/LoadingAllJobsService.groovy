package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.JobDTO
import com.lvg.weldercenter.se.ui.tasks.job.JobTask
import javafx.collections.ObservableList
import javafx.concurrent.Service
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component

@Component
class LoadingAllJobsService extends Service<ObservableList<JobDTO>> implements OnceStartedService{

    @Autowired
    ConfigurableApplicationContext ctx

    private boolean startedOnce = false

    @Override
    protected Task<ObservableList<JobDTO>> createTask() {
        return ctx.getBean(JobTask.class)
    }

    @Override
    boolean isStartedOnce() {
        return startedOnce
    }

    @Override
    void setStartedOnceFlag(boolean startedOnce) {
        this.startedOnce = startedOnce
    }
}
