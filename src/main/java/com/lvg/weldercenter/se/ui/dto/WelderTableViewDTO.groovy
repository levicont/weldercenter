package com.lvg.weldercenter.se.ui.dto

import com.lvg.weldercenter.se.models.Welder
import javafx.beans.Observable
import javafx.beans.property.LongProperty
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import static DTOConstants.*

class WelderTableViewDTO implements ModelDTO{

    final LongProperty id = new SimpleLongProperty()
    final StringProperty name = new SimpleStringProperty()
    final StringProperty surname = new SimpleStringProperty()
    final StringProperty secondName = new SimpleStringProperty()
    final StringProperty birthday = new SimpleStringProperty()
    final StringProperty organization = new SimpleStringProperty()
    final Map<String, Object> PROPERTIES = ['id':id,
                                            'name':name,
                                            'surname':surname,
                                            'secondName':secondName,
                                            'birthday':birthday,
                                            'organization':organization]

    WelderTableViewDTO(Long id, String name, String surname,
                       String secondName, LocalDate birthday, String organization){
        this.id.set(id)
        this.name.set(name)
        this.surname.set(surname)
        this.secondName.set(secondName)
        this.birthday.set(this.birthday == null ? NULL_FIELD_PLACEHOLDER
                            : birthday.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)) )
        this.organization.set(organization)

    }

    Object getPropertyByName(String propertyName){
        return PROPERTIES.get(propertyName)
    }

    StringProperty getStringProperty(String propertyName){
        if (propertyName == 'name') return name
        if (propertyName == 'secondName') return secondName
        if (propertyName == 'surname') return surname
        if (propertyName == 'organization') return organization
        if (propertyName == 'birthday') return birthday
        throw new IllegalArgumentException("Property name: ${propertyName} is invalid")
    }

    Map<String, Object> getProperties(){
        return PROPERTIES
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

    String getBirthday() {
        return birthday.get()
    }

    String getOrganization() {
        return organization.get()
    }

    LongProperty getIdProperty(){
        return id
    }

    StringProperty getNameProperty(){
        return name
    }

    StringProperty getSurnameProperty(){
        return surname
    }

    StringProperty getSecondNameProperty(){
        return secondName
    }

    StringProperty getBirthdayProperty(){
        return birthday
    }

    StringProperty getOrganizationProperty(){
        return organization
    }

    @Override
    String toString() {
        return "${id.get()} ${name.get()} ${secondName.get()} ${surname.get()}"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null) return false
        if (getClass() != o.class) return false

        WelderTableViewDTO that = (WelderTableViewDTO) o

        if (id.value != that.id.value) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
}
