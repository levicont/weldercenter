package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Qualification implements Serializable{

    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name = 'ID')
    Long id
    @Column(name = 'TYPE')
    String type

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof Qualification)) return false

        Qualification that = (Qualification) o

        if (id != that.id) return false
        if (type != that.type) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (type != null ? type.hashCode() : 0)
        return result
    }

    String toString(){
        return type
    }
}
