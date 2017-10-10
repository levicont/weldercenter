package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Size

@Entity
class Teacher implements Serializable{

    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name='ID')
    Long id

    @Column(name = 'NAME', nullable = false)
    @Size(min = 2, max=30)
    String name

    @Column(name = 'SURNAME', nullable = false)
    @Size(min = 2, max=30)
    String surname

    @Column(name = 'SECOND_NAME', nullable = false)
    @Size(min = 2, max=30)
    String secondName

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Teacher teacher = (Teacher) o

        if (id != teacher.id) return false
        if (name != teacher.name) return false
        if (secondName != teacher.secondName) return false
        if (surname != teacher.surname) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (name != null ? name.hashCode() : 0)
        result = 31 * result + (surname != null ? surname.hashCode() : 0)
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0)
        return result
    }

    String toString(){
        return "$surname $name $secondName"
    }
}
