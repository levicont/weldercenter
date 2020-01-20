package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadAllEducationsChangeStateListener
import com.lvg.weldercenter.se.ui.repositories.EducationDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllEducationsService
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import javafx.beans.property.ListProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EducationRepositoryImpl implements EducationDTORepository{
    private static final Logger LOGGER = Logger.getLogger(EducationRepositoryImpl.class)

    @Autowired
    LoadingAllEducationsService loadingAllEducationsService

    @Autowired
    LoadAllEducationsChangeStateListener loadAllEducationsChangeStateListener


    private ObservableList<String> allEducations = FXCollections.observableArrayList()
    private final ListProperty<String> allEducationsProperty =
            new SimpleListProperty<String>(allEducations)

    @Override
    ObservableList<String> getAllEducationDTO() {
       return allEducationsProperty.get()
    }

    @Override
    ListProperty<String> getAllEducationProperty() {
        return allEducationsProperty
    }

    @Override
    void updateEducationDTOList(ObservableList<String> list) {
        allEducations.clear()
        allEducations.addAll(list)
    }

    void loadEducations(){
        //TODO if educationList is relevant do not touch DB. Get list from buffer.
        if(allEducationsProperty.isEmpty()){
            loadingAllEducationsService.stateProperty().addListener(loadAllEducationsChangeStateListener)
            ServiceUtils.startService(loadingAllEducationsService)
            LOGGER.debug("Load educations performed")
        }
    }
}
