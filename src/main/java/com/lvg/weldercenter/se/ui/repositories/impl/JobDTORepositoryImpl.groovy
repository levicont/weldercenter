package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.ui.dto.JobDTO
import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadAllJobChangeStateListener
import com.lvg.weldercenter.se.ui.repositories.JobDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllJobsService
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import javafx.beans.InvalidationListener
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

    private final ListProperty<JobDTO> allDTOProperty = new SimpleListProperty<>()
    private final ListProperty<String> allJobsNameProperty = new SimpleListProperty<>(FXCollections.observableArrayList())

    JobDTORepositoryImpl(){
        initListeners()
    }

    @Override
    ListProperty<JobDTO> getAllDTO() {
        return allDTOProperty
    }

    @Override
    void refreshAllDTO(ObservableList<JobDTO> list) {
        allDTOProperty.set(list)
    }

    @Override
    void loadAllDTO() {
        loadingAllJobsService.stateProperty().addListener(loadAllJobChangeStateListener)
        ServiceUtils.startService(loadingAllJobsService)
    }

    @Override
    ObservableList<String> jobsNameList() {
        return allJobsNameProperty.get()
    }

    @Override
    ListProperty<String> jobsNameListProperty() {
        return allJobsNameProperty
    }

    private initListeners(){
        allDTOProperty.addListener((InvalidationListener){
            allJobsNameProperty.get().clear()
            allDTOProperty.get().each { jobDTO -> allJobsNameProperty.get().add(jobDTO.name)}
        })
    }
}
