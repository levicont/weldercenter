package com.lvg.weldercenter.se.ui.tasks.qualification

import com.lvg.weldercenter.se.models.Qualification
import com.lvg.weldercenter.se.services.QualificationService
import javafx.application.Platform
import javafx.beans.property.ReadOnlyObjectProperty
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import static com.lvg.weldercenter.se.ui.tasks.TaskConstants.ALL_QUALIFICATIONS_TASK_TITLE_MESSAGE
import static com.lvg.weldercenter.se.ui.tasks.TaskConstants.TASK_CANCELED_MESSAGE

@Component
@Scope('prototype')
class QualificationsTask extends Task<ObservableList<String>> {

    @Autowired
    QualificationService qualificationService

    private ReadOnlyObjectWrapper<ObservableList<String>> partialResults =
            new ReadOnlyObjectWrapper<>(this, "partialResults",
                    FXCollections.observableArrayList(new ArrayList<String>()))

    final ObservableList<String> getPartialResults() {
        return partialResults.get()
    }

    final ReadOnlyObjectProperty<ObservableList<String>> partialResultsProperty() {
        partialResults.getReadOnlyProperty()
    }


    @Override
    protected ObservableList<String> call() throws Exception {
        updateTitle(ALL_QUALIFICATIONS_TASK_TITLE_MESSAGE)

        long count = qualificationService.count()
        long counter = 0


        for (Qualification qualification : qualificationService.getAll()) {
            if (this.isCancelled()) {
                updateMessage(TASK_CANCELED_MESSAGE)
                break
            }
            final String qualificationType = qualification.type
            Platform.runLater({
                getPartialResults().add(qualificationType)
            }
            )
            counter++
            updateMessage("Added $counter qualifications to the list")
            updateProgress(counter, count)
        }
        return partialResults.get()

    }
}
