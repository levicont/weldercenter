package com.lvg.weldercenter.se.ui.tasks.qualification

import com.lvg.weldercenter.se.models.Qualification
import com.lvg.weldercenter.se.services.QualificationService
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import static com.lvg.weldercenter.se.ui.tasks.TaskConstants.ALL_QUALIFICATIONS_TASK_TITLE_MESSAGE

@Component
@Scope('prototype')
class QualificationsTask extends Task<ObservableList<String>> {

    @Autowired
    QualificationService qualificationService

    @Override
    protected ObservableList<String> call() throws Exception {
        ObservableList<String> results = FXCollections.observableArrayList()
        updateTitle(ALL_QUALIFICATIONS_TASK_TITLE_MESSAGE)

        long count = qualificationService.count()
        long counter = 0


        for(Qualification qualification : qualificationService.getAll()){
            if (this.isCancelled()){
                break
            }
            results.add(qualification.type)
            counter++

            updateMessage("Added $counter qualifications to the list")
            updateValue(FXCollections.unmodifiableObservableList(results))
            updateProgress(counter, count)
        }
        return results

    }
}
