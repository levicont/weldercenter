package com.lvg.weldercenter.se.ui.dto

import javafx.beans.property.LongProperty
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import static com.lvg.weldercenter.se.ui.dto.ModelsConstants.*

class WelderTableViewDTO {

    final LongProperty id = new SimpleLongProperty()
    final StringProperty name = new SimpleStringProperty()
    final StringProperty surname = new SimpleStringProperty()
    final StringProperty secondName = new SimpleStringProperty()
    final StringProperty birthday = new SimpleStringProperty()
    final StringProperty organization = new SimpleStringProperty()

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
}
