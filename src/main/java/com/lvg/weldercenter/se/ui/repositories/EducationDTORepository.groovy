package com.lvg.weldercenter.se.ui.repositories

import javafx.beans.property.ObjectProperty
import javafx.collections.ObservableList
import org.springframework.stereotype.Component

@Component
interface EducationDTORepository {

    ObservableList<String> getAllEducationDTO()
    ObjectProperty<ObservableList<String>> getAllEducationProperty()
    void updateEducationDTOList(ObservableList<String> list)
}
