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
    protected Long id

    @NotNull
    @Size(min = 2, max = 50)
    protected String name

    @NotNull
    @Size(min = 2, max = 50)
    protected String surname

    @NotNull
    @Size(min = 2, max = 50)
    protected String secname

    @Convert(converter = LocalDateConverter.class)
    protected LocalDate birthday

    @Convert(converter = LocalDateConverter.class)
    protected LocalDate dateBegin

    @Size(max = 10)
    protected String docNumber

    protected String address
    protected String education
    protected String qualification
    protected String job

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = 'organization_id')
    protected Organization organization

    Long getId() {
        return id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getSurname() {
        return surname
    }

    void setSurname(String surname) {
        this.surname = surname
    }

    String getSecname() {
        return secname
    }

    void setSecname(String secname) {
        this.secname = secname
    }

    LocalDate getBirthday() {
        return birthday
    }

    void setBirthday(LocalDate birthday) {
        this.birthday = birthday
    }

    LocalDate getDateBegin() {
        return dateBegin
    }

    void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin
    }

    String getDocNumber() {
        return docNumber
    }

    void setDocNumber(String docNumber) {
        this.docNumber = docNumber
    }

    String getAddress() {
        return address
    }

    void setAddress(String address) {
        this.address = address
    }

    String getEducation() {
        return education
    }

    void setEducation(String education) {
        this.education = education
    }

    String getQualification() {
        return qualification
    }

    void setQualification(String qualification) {
        this.qualification = qualification
    }

    String getJob() {
        return job
    }

    void setJob(String job) {
        this.job = job
    }

    Organization getOrganization() {
        return organization
    }

    void setOrganization(Organization organization) {
        this.organization = organization
    }

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
}
