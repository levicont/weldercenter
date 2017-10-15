package com.lvg.weldercenter.se.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

import static com.lvg.weldercenter.se.cfg.R.ModelsConfig.ID_GENERATOR_NAME

@Entity
class Organization implements Serializable{

    @Id
    @GeneratedValue(generator = ID_GENERATOR_NAME)
    @Column(name = "ID")
    Long id
    @Column(name = "NAME")
    String name
    @Column(name = "ADDRESS")
    String address
    @Column(name = "PHONE")
    String phone

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof Organization)) return false

        Organization that = (Organization) o

        if (id != that.id) return false
        if (name != that.name) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (name != null ? name.hashCode() : 0)
        return result
    }

    String toString(){
        return "$name, $address, $phone"
    }
}
