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
        if (!(o instanceof Education)) return false

        Education education1 = (Education) o

        if (education != education1.education) return false
        if (id != education1.id) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (education != null ? education.hashCode() : 0)
        return result
    }

    String toString(){
        return education
    }

}
