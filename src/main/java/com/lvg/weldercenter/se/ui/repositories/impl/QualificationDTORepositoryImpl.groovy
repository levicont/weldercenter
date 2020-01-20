package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.ui.listeners.welderspane.LoadAllQualificationsChangeStateListener
import com.lvg.weldercenter.se.ui.repositories.QualificationDTORepository
import com.lvg.weldercenter.se.ui.services.LoadingAllQualificationsService
import com.lvg.weldercenter.se.ui.utils.ServiceUtils
import javafx.beans.property.ListProperty
import javafx.beans.property.SimpleListProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class QualificationDTORepositoryImpl implements QualificationDTORepository{

    @Autowired
    LoadingAllQualificationsService loadingAllQualificationsService
    @Autowired
    LoadAllQualificationsChangeStateListener loadAllQualificationsChangeStateListener


    private final ListProperty<String> qualificationListProperty = new SimpleListProperty<>()


    @Override
    void loadQualifications() {
        //TODO if qualificationList is relevant do not touch DB. Get list from buffer.
        if(qualificationListProperty.isEmpty()){
            loadingAllQualificationsService.stateProperty().addListener(loadAllQualificationsChangeStateListener)
            ServiceUtils.startService(loadingAllQualificationsService)
        }

    }

    @Override
    void updateQualificationDTOList(List<String> list) {
        ObservableList<String> result = FXCollections.observableArrayList()
        list.stream().forEach({q -> result.add(q)})
        qualificationListProperty.set(result)
    }

    @Override
    ListProperty<String> qualificationsProperty() {
        return qualificationListProperty
    }
}
