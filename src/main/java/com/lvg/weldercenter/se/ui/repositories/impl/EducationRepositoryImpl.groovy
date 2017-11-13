package com.lvg.weldercenter.se.ui.repositories.impl

import com.lvg.weldercenter.se.ui.dto.EducationDTO
import com.lvg.weldercenter.se.ui.repositories.EducationDTORepository
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.springframework.stereotype.Component

@Component
class EducationRepositoryImpl implements EducationDTORepository{



    private ObservableList<EducationDTO> allEducations = FXCollections.observableArrayList()
    private final ObjectProperty<ObservableList<EducationDTO>> allEducationsProperty =
            new SimpleObjectProperty<ObservableList<EducationDTO>>(allEducations)

    @Override
    ObservableList<EducationDTO> getAllEducationDTO() {
       return allEducationsProperty.get()
    }

    @Override
    ObjectProperty<ObservableList<EducationDTO>> getAllEducationProperty() {
        return allEducationsProperty
    }

    @Override
    void updateEducationDTOList(ObservableList<EducationDTO> list) {
        allEducations.addAll(list)
    }
}
