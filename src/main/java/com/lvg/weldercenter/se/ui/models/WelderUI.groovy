package com.lvg.weldercenter.se.ui.models

import com.lvg.weldercenter.se.models.Welder
import javafx.beans.property.*

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import static com.lvg.weldercenter.se.ui.models.ModelsConstants.*

class WelderUI extends GenericModelUI{

    final Welder welder

    final LongProperty id = new SimpleLongProperty()
    final StringProperty name = new SimpleStringProperty()
    final StringProperty surname = new SimpleStringProperty()
    final StringProperty secondName = new SimpleStringProperty()
    final ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<LocalDate>()
    final ObjectProperty<LocalDate> dateBegin = new SimpleObjectProperty<LocalDate>()
    final StringProperty documentNumber = new SimpleStringProperty()
    final StringProperty address = new SimpleStringProperty()
    final StringProperty education = new SimpleStringProperty()
    final StringProperty qualification = new SimpleStringProperty()
    final StringProperty job = new SimpleStringProperty()
    final ObjectProperty<OrganizationUI> organization = new SimpleObjectProperty<OrganizationUI>()


    WelderUI(Welder welder){
        this.welder = welder
        this.id.set(welder.id == null ? NULL_ID_FIELD_DEFAULT : welder.id)
        this.name.set(welder.name)
        this.surname.set(welder.surname)
        this.secondName.set(welder.secondName)
        this.birthday.set(welder.birthday)
        this.dateBegin.set(welder.dateBegin)
        this.documentNumber.set(welder.documentNumber)
        this.address.set(welder.address)
        this.education.set(welder.education)
        this.qualification.set(welder.qualification)
        this.job.set(welder.job)
        this.organization.set(welder.organization != null ? new OrganizationUI(welder.organization) : null)

    }

    Welder getWelder(){
        welder.id = id.get()
        welder.name = name.get()
        welder.surname = surname.get()
        welder.secondName = secondName.get()
        welder.birthday = birthday.get()
        welder.dateBegin = dateBegin.get()
        welder.documentNumber = documentNumber.get()
        welder.address = address.get()
        welder.education = education.get()
        welder.qualification = qualification.get()
        welder.job = job.get()
        welder.organization = (organization.get() != null ? organization.get().getOrganization() : null)
        return welder
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        WelderUI welderUI = (WelderUI) o

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

    Long getId() {
        return getWelder().id
    }

    String getName() {
        return getWelder().name
    }

    String getSurname() {
        return getWelder().surname
    }

    String getSecondName() {
        return getWelder().secondName
    }

    LocalDate getBirthday() {
        return getWelder().birthday
    }

    LocalDate getDateBegin() {
        return getWelder().dateBegin
    }

    String getDocumentNumber() {
        return getWelder().documentNumber
    }

    String getAddress() {
        return getWelder().address
    }

    String getEducation() {
        return getWelder().education
    }

    String getQualification() {
        return getWelder().qualification
    }

    String getJob() {
        return getWelder().job
    }

    OrganizationUI getOrganization() {
        return organization.get()
    }

    String getBirthdayFormat() {
        def birthday = getWelder().birthday
        return birthday == null ? NULL_FIELD_PLACEHOLDER:
                birthday.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN))
    }

    String getDateBeginFormat(){
        def dateBegin = getWelder().dateBegin
        return dateBegin == null ? NULL_FIELD_PLACEHOLDER:
                dateBegin.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN))
    }

    String getOrganizationName() {
        return organization.get()==null? NULL_FIELD_PLACEHOLDER:
                organization.get().name.get()
    }

    void setName(String name){
        this.name.set(name)
        this.welder.name = name
    }

    void setSurname(String surname){
        this.welder.surname = surname
        this.surname.set(surname)
    }


    void setSecondName(String secondName){
        this.welder.secondName = secondName
        this.secondName.set(secondName)
    }

    void setBirthday(LocalDate birthday){
        this.welder.birthday = birthday
        this.birthday.set(birthday)
    }

    void setDateBegin(LocalDate dateBegin){
        this.welder.dateBegin = dateBegin
        this.dateBegin.set(dateBegin)
    }

    void setDocumentNumber(String documentNumber){
        this.welder.documentNumber = documentNumber
        this.documentNumber.set(documentNumber)
    }

    void setAddress(String address){
        this.welder.address = address
        this.address.set(address)
    }

    void setEducation(String education){
        this.welder.education = education
        this.education.set(education)
    }

    void setQualification(String qualification){
        this.welder.qualification = qualification
        this.qualification.set(qualification)
    }

    void setJob(String job){
        this.welder.job = job
        this.job.set(job)
    }


}
