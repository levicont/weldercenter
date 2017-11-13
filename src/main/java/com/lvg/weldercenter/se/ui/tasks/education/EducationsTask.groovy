package com.lvg.weldercenter.se.ui.tasks.education

import com.lvg.weldercenter.se.models.Education
import com.lvg.weldercenter.se.services.EducationService
import com.lvg.weldercenter.se.ui.dto.EducationDTO
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import static com.lvg.weldercenter.se.ui.tasks.TaskConstants.ALL_EDUCATIONS_TASK_TITLE_MESSAGE

@Component
@Scope('prototype')
class EducationsTask extends Task<ObservableList<EducationDTO>>{

    @Autowired
    EducationService educationService


    @Override
    protected ObservableList<EducationDTO> call() throws Exception {
        ObservableList<EducationDTO> results = FXCollections.observableArrayList()

        long count = educationService.count()
        long counter = 0


        updateTitle(ALL_EDUCATIONS_TASK_TITLE_MESSAGE)
        for(Education education : educationService.getAll()){
            if (this.isCancelled()){
                break
            }
            results.add(new EducationDTO(education))
            counter++

            updateMessage("Added $counter education to the list")
            updateValue(FXCollections.unmodifiableObservableList(results))
            updateProgress(counter, count)
        }
        return results
    }


}
