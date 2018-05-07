package com.lvg.weldercenter.se.ui.tasks.job

import com.lvg.weldercenter.se.exceptions.WelderCenterException
import com.lvg.weldercenter.se.models.Job
import com.lvg.weldercenter.se.services.JobService
import com.lvg.weldercenter.se.ui.dto.JobDTO
import com.lvg.weldercenter.se.ui.tasks.TaskConstants
import javafx.application.Platform
import javafx.beans.property.ReadOnlyObjectProperty
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope('prototype')
class JobTask extends Task<ObservableList<JobDTO>>{
    private static final Logger LOGGER = Logger.getLogger(JobTask.class)

    private ReadOnlyObjectWrapper<ObservableList<JobDTO>> partialResults =
            new ReadOnlyObjectWrapper<>(this, "partialResults",
                    FXCollections.observableArrayList(new ArrayList<JobDTO>()))

    final ObservableList<JobDTO> getPartialResults(){
        return partialResults.get()
    }
    final ReadOnlyObjectProperty<ObservableList<JobDTO>> partialResultsProperty(){
        partialResults.getReadOnlyProperty()
    }

    @Autowired
    JobService jobService

    @Override
    protected ObservableList<JobDTO> call() throws Exception {
        try {
            updateTitle(TaskConstants.ALL_JOBS_TASK_TITLE_MESSAGE)

            long count = jobService.count()
            long counter = 0

            for (Job job : jobService.getAll()) {
                if (this.isCancelled()) break
                final JobDTO jobDTO = new JobDTO(job)
                Platform.runLater({
                        getPartialResults().add(jobDTO)
                })
                counter++
                updateProgress(counter, count)
            }
            return partialResults.get()
        }catch(Exception ex){
            LOGGER.warn("JobTask: exception ex: ${ex.message}")
            ex.printStackTrace()
            throw new WelderCenterException("Exception during JobTask", ex)
        }
    }
}
