package com.lvg.weldercenter.se.ui.models

import com.lvg.weldercenter.se.models.Welder
import javafx.beans.property.LongProperty
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

import java.time.LocalDate

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
}
