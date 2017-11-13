package com.lvg.weldercenter.se.ui.dto

import com.lvg.weldercenter.se.models.Education
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty

class EducationDTO extends GenericModelDTO<Education>{

    private final Education education
    private final ObjectProperty<Education> educationProperty = new SimpleObjectProperty<>()

    EducationDTO(Education education){
        this.education= education
        this.educationProperty.set(education)
        validateModel(education)
    }

    @Override
    Long getId() {
        return education.id == null ? DTOConstants.NULL_ID_FIELD_DEFAULT : education.id
    }

    String getType(){
        return education.education
    }

    void setType(String type){
        education.education = type
    }

    Education getEducation(){
        return education
    }

    @Override
    String toString() {
        return education.toString()
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        EducationDTO that = (EducationDTO) o

        if (getEducation().education != that.getEducation().education) return false

        return true
    }

    int hashCode() {
        return education.education.hashCode()
    }
}
