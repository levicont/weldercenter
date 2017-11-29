package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.ui.repositories.EducationDTORepository
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.springframework.stereotype.Component

@Component
class EducationRepositoryImpl implements EducationDTORepository{



    private ObservableList<String> allEducations = FXCollections.observableArrayList()
    private final ObjectProperty<ObservableList<String>> allEducationsProperty =
            new SimpleObjectProperty<ObservableList<String>>(allEducations)

    @Override
    ObservableList<String> getAllEducationDTO() {
       return allEducationsProperty.get()
    }

    @Override
    ObjectProperty<ObservableList<String>> getAllEducationProperty() {
        return allEducationsProperty
    }

    @Override
    void updateEducationDTOList(ObservableList<String> list) {
        allEducations.addAll(list)
    }
}
