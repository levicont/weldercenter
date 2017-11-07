package com.lvg.weldercenter.se.ui.models

import com.lvg.weldercenter.se.models.Welder
import javafx.beans.property.*

import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
        this.id.set(welder.id)
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
        welder.name = name
        welder.surname = surname
        welder.secondName = secondName
        welder.birthday = birthday.get()
        welder.dateBegin = dateBegin.get()
        welder.documentNumber = documentNumber
        welder.address = welder.address
        welder.education = welder.education
        welder.qualification = welder.qualification
        welder.job = welder.job
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
        return id.get()
    }

    String getName() {
        return name.get()
    }

    String getSurname() {
        return surname.get()
    }

    String getSecondName() {
        return secondName.get()
    }

    LocalDate getBirthday() {
        return birthday.get()
    }

    LocalDate getDateBegin() {
        return dateBegin.get()
    }

    String getDocumentNumber() {
        return documentNumber.get()
    }

    String getAddress() {
        return address.get()
    }

    String getEducation() {
        return education.get()
    }

    String getQualification() {
        return qualification.get()
    }

    String getJob() {
        return job.get()
    }

    OrganizationUI getOrganization() {
        return organization.get()
    }

    String getBirthdayFormat() {
        return birthday.get() == null ? '': birthday.get().format(DateTimeFormatter.ofPattern('dd.MM.yyyy'))
    }

    String getOrganizationName() {

        return organization.get()==null? '':organization.get().name.get()
    }
}
