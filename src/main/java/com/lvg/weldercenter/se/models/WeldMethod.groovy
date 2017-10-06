package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Size


@Entity
class WeldMethod implements Serializable{

    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name = "ID")
    Long id

    @Column(name = "NAME")
    String name

    @Column(name = "CODE")
    @Size(max = 3)
    String code

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        WeldMethod that = (WeldMethod) o

        if (code != that.code) return false
        if (id != that.id) return false
        if (name != that.name) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (name != null ? name.hashCode() : 0)
        result = 31 * result + (code != null ? code.hashCode() : 0)
        return result
    }

    String toString(){
        return "$name ($code)"
    }
}
