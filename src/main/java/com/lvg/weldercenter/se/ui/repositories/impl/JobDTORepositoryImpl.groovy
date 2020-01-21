package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.ui.dto.JobDTO
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadAllJobChangeStateListener
import com.lvg.weldercenter.se.ui.repositories.JobDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllJobsService
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import javafx.beans.property.ListProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class JobDTORepositoryImpl implements JobDTORepository{

    @Autowired
    LoadAllJobChangeStateListener loadAllJobChangeStateListener
    @Autowired
    LoadingAllJobsService loadingAllJobsService

    private final ListProperty<String> jobsNameListProperty =
            new SimpleListProperty<>(FXCollections.observableArrayList())



    @Override
    void refreshAllDTO(ObservableList<JobDTO> list) {
       jobsNameListProperty.clear()
       list.forEach({jobDTO ->
           jobsNameListProperty.add(jobDTO.name)
       })
    }

    @Override
    void loadAllDTO() {
        //TODO if jobsNameList is relevant do not touch DB. Get list from buffer.
        if (jobsNameListProperty.isEmpty()) {
            loadJobListFromDB()
        }
    }

    private void loadJobListFromDB(){
        loadingAllJobsService.stateProperty().addListener(loadAllJobChangeStateListener)
        ServiceUtils.startService(loadingAllJobsService)

    }

    @Override
    ObservableList<String> jobsNameList() {
        return jobsNameListProperty.get()
    }

    @Override
    ListProperty<String> jobsNameListProperty() {
        return jobsNameListProperty
    }

}
