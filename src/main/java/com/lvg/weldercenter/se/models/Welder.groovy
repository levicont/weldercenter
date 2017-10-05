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

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "BIRTHDAY")
    LocalDate birthday

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "DATE_BEGIN")
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = 'ORGANIZATION_ID')
    Organization organization


    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Welder welder = (Welder) o

        if (id != welder.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }

    String toString(){
        return "$surname $name $secondName"
    }
}
