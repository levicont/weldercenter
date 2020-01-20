package com.lvg.weldercenter.se.ui.repositories

import javafx.beans.property.ListProperty
import javafx.collections.ObservableList
import org.springframework.stereotype.Component

@Component
interface EducationDTORepository {

    ObservableList<String> getAllEducationDTO()
    ListProperty<String> getAllEducationProperty()
    void updateEducationDTOList(ObservableList<String> list)
    void loadEducations()
}
