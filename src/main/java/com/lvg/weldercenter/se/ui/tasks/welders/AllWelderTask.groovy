package com.lvg.weldercenter.se.ui.tasks.welders

import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.services.WelderService
import com.lvg.weldercenter.se.ui.dto.WelderDTO
import com.lvg.weldercenter.se.ui.tasks.TaskConstants
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

    @Override
    protected ObservableList<WelderDTO> call() throws Exception {
        ObservableList<WelderDTO> results = FXCollections.observableArrayList()

        long count = welderService.count()
        long counter = 0



        updateTitle(ALL_WELDERS_TASK_TITLE_MESSAGE)
        for(Welder welder : welderService.getAll()){
            if (this.isCancelled()){
                break
            }
            def welderUI = new WelderDTO(welder)
            results.add(welderUI)
            counter++

            updateMessage("Added $counter welders to the list")
            updateValue(FXCollections.unmodifiableObservableList(results))
            updateProgress(counter, count)
        }

        return results
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
