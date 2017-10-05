package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Created by Victor on 05.10.2017.
 */
@Entity
class Education implements Serializable{
    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name = 'ID')
    Long id
    @Column(name = 'EDUCATION')
    String education

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Education education = (Education) o

        if (id != education.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }

    String toString(){
        return education
    }

}
