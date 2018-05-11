package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.dto.JobDTO
import com.lvg.weldercenter.se.ui.tasks.job.JobTask
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.stereotype.Component

@Component
class LoadingAllJobsService extends UIService<ObservableList<JobDTO>> {

    @Override
    protected Task<ObservableList<JobDTO>> getTask() {
        return ctx.getBean(JobTask.class)
    }
}
