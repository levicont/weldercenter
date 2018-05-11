package com.lvg.weldercenter.se.ui.services

import com.lvg.weldercenter.se.ui.tasks.education.EducationsTask
import javafx.collections.ObservableList
import javafx.concurrent.Task
import org.springframework.stereotype.Component

@Component
class LoadingAllEducationsService extends UIService<ObservableList<String>>{

    @Override
    protected Task<ObservableList<String>> getTask() {
        return ctx.getBean(EducationsTask.class)
    }
}
