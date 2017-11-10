package com.lvg.weldercenter.se.ui.tasks.welders

import com.lvg.weldercenter.se.services.WelderService
import com.lvg.weldercenter.se.ui.dto.WelderTableViewDTO
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope('prototype')
class AllWeldersTableViewTask extends Task<ObservableList<WelderTableViewDTO>> implements TaskConstants{

    @Autowired
    WelderService welderService


    @Override
    protected ObservableList<WelderTableViewDTO> call() throws Exception {
        ObservableList<WelderTableViewDTO> results = FXCollections.observableArrayList()

        long count = welderService.count()
        long counter = 0


        updateTitle(ALL_WELDERS_TASK_TITLE_MESSAGE)
        for(WelderTableViewDTO welder : welderService.getAllWeldersTableViewDTO()){
            if (this.isCancelled()){
                break
            }
            results.add(welder)
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
