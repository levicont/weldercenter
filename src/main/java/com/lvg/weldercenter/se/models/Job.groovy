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
class Job implements Serializable{

    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name = 'ID')
    Long id

    @Column(name = 'NAME')
    String name

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof Job)) return false

        Job job = (Job) o

        if (id != job.id) return false
        if (name != job.name) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (name != null ? name.hashCode() : 0)
        return result
    }

    String toString(){
        return name
    }
}
