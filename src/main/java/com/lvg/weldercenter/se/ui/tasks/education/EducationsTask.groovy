package com.lvg.weldercenter.se.ui.tasks.education

import com.lvg.weldercenter.se.models.Education
import com.lvg.weldercenter.se.services.EducationService
import com.lvg.weldercenter.se.ui.dto.EducationDTO
import javafx.application.Platform
import javafx.beans.property.ReadOnlyObjectProperty
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import static com.lvg.weldercenter.se.ui.tasks.TaskConstants.ALL_EDUCATIONS_TASK_TITLE_MESSAGE

@Component
@Scope('prototype')
class EducationsTask extends Task<ObservableList<String>>{

    private ReadOnlyObjectWrapper<ObservableList<String>> partialResults =
            new ReadOnlyObjectWrapper<>(this, "partialResults",
                    FXCollections.observableArrayList(new ArrayList<String>()))

    final ObservableList<String> getPartialResults(){
        return partialResults.get()
    }
    final ReadOnlyObjectProperty<ObservableList<String>> partialResultsProperty(){
        partialResults.getReadOnlyProperty()
    }

    @Autowired
    EducationService educationService


    @Override
    protected ObservableList<String> call() throws Exception {
        updateTitle(ALL_EDUCATIONS_TASK_TITLE_MESSAGE)

        long count = educationService.count()
        long counter = 0


        for(Education education : educationService.getAll()){
            if (isCancelled()) break
            final String educationName = education.education
            Platform.runLater({
                getPartialResults().add(educationName)
            })
            counter++
            updateProgress(counter, count)
        }
        return partialResults.get()
    }


}
