package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = 'weld_wire')
class WeldWire implements Serializable{

    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name = 'ID')
    Long id

    @Column(name = 'TYPE')
    String type

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        WeldWire weldWire = (WeldWire) o

        if (id != weldWire.id) return false
        if (type != weldWire.type) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (type != null ? type.hashCode() : 0)
        return result
    }

    @Override
    String toString() {
        return "$type"
    }
}
