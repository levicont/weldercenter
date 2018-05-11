package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.tasks.qualification.QualificationsTask
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.stereotype.Component

@Component
class LoadingAllQualificationsService extends UIService<ObservableList<String>> {

    @Override
    protected Task<ObservableList<String>> getTask() {
        return ctx.getBean(QualificationsTask.class)
    }
}
