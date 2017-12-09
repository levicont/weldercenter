package com.lvg.weldercenter.se.ui.dto

import com.lvg.weldercenter.se.models.Welder
import javafx.beans.property.ObjectProperty
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
        validateModel(welder)
        welderProperty.set(welder)
        this.welder = welder


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
        welder.name = getName()
        welder.surname = getSurname()
        welder.secondName = getSecondName()
        welder.birthday = getBirthday()
        welder.dateBegin = getDateBegin()
        welder.documentNumber = getDocumentNumber()
        welder.address = getAddress()
        welder.qualification = getQualification()
        welder.education = getEducation()
        welder.job = getJob()

        return welder
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        WelderDTO welderDTO = (WelderDTO) o

        if (organizationProperty != welderDTO.organizationProperty()) return false
        if (getAddress() != welderDTO.getAddress()) return false
        if (getBirthday() != welderDTO.getBirthday()) return false
        if (getDateBegin() != welderDTO.getDateBegin()) return false
        if (getDocumentNumber() != welderDTO.getDocumentNumber()) return false
        if (getEducation() != welderDTO.getEducation()) return false
        if (getJob() != welderDTO.getJob()) return false
        if (getName() != welderDTO.getName()) return false
        if (getQualification() != welderDTO.getQualification()) return false
        if (getSecondName() != welderDTO.getSecondName()) return false
        if (getSurname() != welderDTO.getSurname()) return false

        return true
    }

    int hashCode() {
        int result
        result = (organizationProperty() != null ? organizationProperty.hashCode() : 0)
        result = 31 * result + (getName() != null ? getName().hashCode() : 0)
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0)
        result = 31 * result + (getSecondName() != null ? getSecondName().hashCode() : 0)
        result = 31 * result + (getDocumentNumber() != null ? getDocumentNumber().hashCode() : 0)
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0)
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0)
        result = 31 * result + (getDateBegin() != null ? getDateBegin().hashCode() : 0)
        result = 31 * result + (getEducation() != null ? getEducation().hashCode() : 0)
        result = 31 * result + (getQualification() != null ? getQualification().hashCode() : 0)
        result = 31 * result + (getJob() != null ? getJob().hashCode() : 0)
        return result
    }

    @Override
    String toString() {
        return getWelder().toString()
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new WelderDTO(welder)
    }

    @Override
    Long getId() {
        def id = getWelder().id
        return id == null ? NULL_ID_FIELD_DEFAULT : id
    }

    String getName() {
        return nameProperty.get()
    }

    String getSurname() {
        return surnameProperty.get()
    }

    String getSecondName() {
        return secondNameProperty.get()
    }

    LocalDate getBirthday() {
        return birthdayProperty.get()
    }

    LocalDate getDateBegin() {
        return dateBeginProperty.get()
    }

    String getDocumentNumber() {
        return documentNumberProperty.get()
    }

    String getAddress() {
        return addressProperty.get()
    }

    String getEducation() {
        return educationProperty.get()
    }

    String getQualification() {
        return qualificationProperty.get()
    }

    String getJob() {
        return jobProperty.get()
    }

    OrganizationDTO getOrganizationDTO() {
        return organizationProperty.get()
    }

    String getBirthdayFormat() {
        def birthday = getBirthday()
        return birthday == null ? NULL_FIELD_PLACEHOLDER :
                birthday.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN))
    }

    String getDateBeginFormat() {
        def dateBegin = getDateBegin()
        return dateBegin == null ? NULL_FIELD_PLACEHOLDER :
                dateBegin.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN))
    }

    String getOrganizationName() {
        return organizationProperty.get() == null ? NULL_FIELD_PLACEHOLDER :
                organizationProperty.get().name
    }

    void setName(String name) {
        nameProperty.set(name)
    }

    void setSurname(String surname) {
        surnameProperty.set(surname)
    }


    void setSecondName(String secondName) {
        secondNameProperty.set(secondName)
    }

    void setBirthday(LocalDate birthday) {
        birthdayProperty.set(birthday)
    }

    void setDateBegin(LocalDate dateBegin) {
        dateBeginProperty.set(dateBegin)
    }

    void setDocumentNumber(String documentNumber) {
        documentNumberProperty.set(documentNumber)
    }

    void setAddress(String address) {
        addressProperty.set(address)
    }

    void setEducation(String education) {
        educationProperty.set(education)
    }

    void setQualification(String qualification) {
        qualificationProperty.set(qualification)
    }

    void setJob(String job) {
        jobProperty.set(job)
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

    ObjectProperty<OrganizationDTO> organizationProperty(){
        return organizationProperty
    }


}
