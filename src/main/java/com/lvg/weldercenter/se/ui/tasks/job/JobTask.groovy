package com.lvg.weldercenter.se.ui.tasks.job

import com.lvg.weldercenter.se.models.Job
import com.lvg.weldercenter.se.services.JobService
import com.lvg.weldercenter.se.ui.dto.JobDTO
import com.lvg.weldercenter.se.ui.tasks.TaskConstants
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope('prototype')
class JobTask extends Task<ObservableList<JobDTO>>{

    @Autowired
    JobService jobService

    @Override
    protected ObservableList<JobDTO> call() throws Exception {
        ObservableList<JobDTO> results = FXCollections.observableArrayList()

        long count = jobService.count()
        long counter = 0

        updateTitle(TaskConstants.ALL_JOBS_TASK_TITLE_MESSAGE)
        for(Job job : jobService.getAll()){
            if (this.isCancelled()){
                break
            }
            results.add(new JobDTO(job))
            counter++
            updateMessage("Added $counter jobs to the list")
            updateValue(FXCollections.unmodifiableObservableList(results))
            updateProgress(counter, count)
        }
        return results
    }
}
