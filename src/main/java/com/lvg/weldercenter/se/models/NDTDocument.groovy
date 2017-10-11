package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = 'ndt_document')
class NDTDocument implements Serializable{

    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name='ID')
    Long id

    @Column(name = 'CODE')
    String code

    @Column(name = 'FULL_TITLE')
    String fullTitle



    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        NDTDocument that = (NDTDocument) o

        if (id != that.id) return false
        if (code != that.code) return false
        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (code != null ? code.hashCode() : 0)
        return result
    }

    String toString(){
        return "$code"
    }
}
