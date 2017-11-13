package com.lvg.weldercenter.se.ui.repositories

import com.lvg.weldercenter.se.ui.dto.EducationDTO
import javafx.beans.property.ListProperty
import javafx.beans.property.ObjectProperty
import javafx.collections.ObservableList
import org.springframework.stereotype.Component

@Component
interface EducationDTORepository {

    ObservableList<EducationDTO> getAllEducationDTO()
    ObjectProperty<ObservableList<EducationDTO>> getAllEducationProperty()
    void updateEducationDTOList(ObservableList<EducationDTO> list)
}
