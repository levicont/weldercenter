package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R

import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinColumns
import javax.persistence.OneToMany
import javax.persistence.OrderBy
import javax.validation.constraints.NotNull

@Entity
class Curriculum implements Serializable{


    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name = 'ID')
    Long id

    @NotNull
    @Column(name = 'TITLE')
    String title

    @Column(name = 'DESCRIPTION')
    String description


    @OneToMany(mappedBy = 'curriculum')
    Set<Section> sections = new LinkedHashSet<>()

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Curriculum that = (Curriculum) o

        if (id != that.id) return false
        if (title != that.title) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (title != null ? title.hashCode() : 0)
        return result
    }

    String toString(){
        return "$title"
    }
}
