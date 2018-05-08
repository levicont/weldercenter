package com.lvg.weldercenter.se.ui.tasks.welders

import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.services.WelderService
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.tasks.TaskConstants
import javafx.application.Platform
import javafx.beans.property.ReadOnlyObjectProperty
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(value = 'prototype')
class AllWelderTask extends Task<ObservableList<WelderDTO>> implements TaskConstants{

    @Autowired
    WelderService welderService

    private ReadOnlyObjectWrapper<ObservableList<WelderDTO>> partialResults =
            new ReadOnlyObjectWrapper<>(this, "partialResults",
                    FXCollections.observableArrayList(new ArrayList<WelderDTO>()))

    final ObservableList<WelderDTO> getPartialResults() {
        return partialResults.get()
    }

    final ReadOnlyObjectProperty<ObservableList<WelderDTO>> partialResultsProperty() {
        partialResults.getReadOnlyProperty()
    }

    @Override
    protected ObservableList<WelderDTO> call() throws Exception {
        updateTitle(ALL_WELDERS_TASK_TITLE_MESSAGE)

        long count = welderService.count()
        long counter = 0

        for(Welder welder : welderService.getAll()){
            if (this.isCancelled()){
                updateMessage(TASK_CANCELED_MESSAGE)
                break
            }
            final WelderDTO welderDTO = new WelderDTO(welder)
            Platform.runLater({getPartialResults().add(welderDTO)})
            counter++

            updateMessage("Added $counter welders to the list")
            updateProgress(counter, count)
        }
        return partialResults.get()
    }

    @Override
    protected void succeeded() {
        super.succeeded()
        updateMessage(TASK_SUCCESS_MESSAGE)
    }

    @Override
    protected void cancelled() {
        super.cancelled()
        updateMessage(TASK_CANCELED_MESSAGE)
    }

    @Override
    protected void failed() {
        super.failed()
        updateMessage(TASK_FAIL_MESSAGE)
    }
}
