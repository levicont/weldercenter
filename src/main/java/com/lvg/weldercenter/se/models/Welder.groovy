package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.converters.LocalDateConverter

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import java.time.LocalDate

@Entity
class Welder implements Serializable{

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    @Column(name = "ID")
    Long id

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "NAME")
    String name

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "SURNAME")
    String surname

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "SECOND_NAME")
    String secondName

    @Column(name = "BIRTHDAY")
    @Convert(converter = LocalDateConverter.class)
    LocalDate birthday

    @Column(name = "DATE_BEGIN")
    @Convert(converter = LocalDateConverter.class)
    LocalDate dateBegin

    @Size(max = 10)
    @Column(name = "DOCUMENT_NUMBER")
    String documentNumber

    @Column(name = "ADDRESS")
    String address
    @Column(name = "EDUCATION")
    String education
    @Column(name = "QUALIFICATION")
    String qualification
    @Column(name = "JOB")
    String job

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = 'ORGANIZATION_ID', foreignKey = @ForeignKey(name = 'FK_WELDER_ORGANIZATION'))
    Organization organization

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof Welder)) return false

        Welder welder = (Welder) o

        if (birthday != welder.birthday) return false
        if (id != welder.id) return false
        if (name != welder.name) return false
        if (secondName != welder.secondName) return false
        if (surname != welder.surname) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (name != null ? name.hashCode() : 0)
        result = 31 * result + (surname != null ? surname.hashCode() : 0)
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0)
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0)
        return result
    }

    String toString(){
        return "$surname $name $secondName"
    }
}
