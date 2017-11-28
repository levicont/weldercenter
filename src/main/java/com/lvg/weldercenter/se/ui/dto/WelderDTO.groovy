package com.lvg.weldercenter.se.ui.dto

import com.lvg.weldercenter.se.models.Welder
import javafx.beans.property.LongProperty
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import static com.lvg.weldercenter.se.ui.dto.DTOConstants.*

class WelderDTO extends GenericModelDTO<Welder> {
    final Welder welder
    final ObjectProperty<Welder> welderProperty = new SimpleObjectProperty<>()
    final ObjectProperty<OrganizationDTO> organizationProperty = new SimpleObjectProperty<OrganizationDTO>()

    final ObjectProperty<Long> idProperty = new SimpleObjectProperty<>()
    final StringProperty nameProperty = new SimpleStringProperty()
    final StringProperty surnameProperty = new SimpleStringProperty()
    final StringProperty secondNameProperty = new SimpleStringProperty()
    final StringProperty documentNumberProperty = new SimpleStringProperty()
    final StringProperty addressProperty = new SimpleStringProperty()
    final ObjectProperty<LocalDate> birthdayProperty = new SimpleObjectProperty<>()
    final ObjectProperty<LocalDate> dateBeginProperty = new SimpleObjectProperty<>()
    final StringProperty educationProperty = new SimpleStringProperty()
    final StringProperty qualificationProperty = new SimpleStringProperty()
    final StringProperty jobProperty = new SimpleStringProperty()


    WelderDTO(Welder welder) {
        welderProperty.set(welder)
        this.welder = welder
        validateModel(welder)

        idProperty.set(welder.id == null ? NULL_ID_FIELD_DEFAULT : welder.id)
        nameProperty.set(welder.name)
        surnameProperty.set(welder.surname)
        secondNameProperty.set(welder.secondName)
        documentNumberProperty.set(welder.documentNumber)
        addressProperty.set(welder.address)
        birthdayProperty.set(welder.birthday)
        dateBeginProperty.set(welder.dateBegin)
        educationProperty.set(welder.education)
        qualificationProperty.set(welder.qualification)
        jobProperty.set(welder.job)

        this.organizationProperty.set(welder.organization != null ? new OrganizationDTO(welder.organization) : null)
    }

    Welder getWelder() {
        welder.organization = (organizationProperty.get() != null ? organizationProperty.get().getOrganization() : null)
        return welder
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        WelderDTO welderUI = (WelderDTO) o

        if (welder != welderUI.welder) return false

        return true
    }

    int hashCode() {
        return welder.hashCode()
    }

    @Override
    String toString() {
        return getWelder().toString()
    }

    @Override
    Long getId() {
        return welder.id == null ? NULL_ID_FIELD_DEFAULT : welder.id
    }

    String getName() {
        return welder.name
    }

    String getSurname() {
        return welder.surname
    }

    String getSecondName() {
        return welder.secondName
    }

    LocalDate getBirthday() {
        return welder.birthday
    }

    LocalDate getDateBegin() {
        return welder.dateBegin
    }

    String getDocumentNumber() {
        return welder.documentNumber
    }

    String getAddress() {
        return welder.address
    }

    String getEducation() {
        return welder.education
    }

    String getQualification() {
        return welder.qualification
    }

    String getJob() {
        return welder.job
    }

    OrganizationDTO getOrganizationDTO() {
        return organizationProperty.get()
    }

    String getBirthdayFormat() {
        def birthday = welder.birthday
        return birthday == null ? NULL_FIELD_PLACEHOLDER :
                birthday.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN))
    }

    String getDateBeginFormat() {
        def dateBegin = welder.dateBegin
        return dateBegin == null ? NULL_FIELD_PLACEHOLDER :
                dateBegin.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN))
    }

    String getOrganizationName() {
        return organizationProperty.get() == null ? NULL_FIELD_PLACEHOLDER :
                organizationProperty.get().name
    }

    void setName(String name) {
        this.welder.name = name
    }

    void setSurname(String surname) {
        this.welder.surname = surname
    }


    void setSecondName(String secondName) {
        this.welder.secondName = secondName
    }

    void setBirthday(LocalDate birthday) {
        this.welder.birthday = birthday
    }

    void setDateBegin(LocalDate dateBegin) {
        this.welder.dateBegin = dateBegin
    }

    void setDocumentNumber(String documentNumber) {
        this.welder.documentNumber = documentNumber
    }

    void setAddress(String address) {
        this.welder.address = address
    }

    void setEducation(String education) {
        this.welder.education = education
    }

    void setQualification(String qualification) {
        this.welder.qualification = qualification
    }

    void setJob(String job) {
        this.welder.job = job
    }

    ObjectProperty<Long> getIdProperty() {
        return idProperty
    }

    StringProperty getNameProperty() {
        return nameProperty
    }

    StringProperty getSurnameProperty() {
        return surnameProperty
    }

    StringProperty getSecondNameProperty() {
        return secondNameProperty
    }

    StringProperty getDocumentNumberProperty() {
        return documentNumberProperty
    }

    StringProperty getAddressProperty() {
        return addressProperty
    }

    ObjectProperty<LocalDate> getBirthdayProperty() {
        return birthdayProperty
    }

    ObjectProperty<LocalDate> getDateBeginProperty() {
        return dateBeginProperty
    }

    StringProperty getEducationProperty() {
        return educationProperty
    }

    StringProperty getQualificationProperty() {
        return qualificationProperty
    }

    StringProperty getJobProperty() {
        return jobProperty
    }
}
