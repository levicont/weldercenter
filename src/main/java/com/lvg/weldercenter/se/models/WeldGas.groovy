package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'weld_gas')
class WeldGas implements Serializable{

    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name = 'ID')
    Long id

    @Column(name = 'TYPE')
    String type

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof WeldGas)) return false

        WeldGas weldGas = (WeldGas) o

        if (id != weldGas.id) return false
        if (type != weldGas.type) return false

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
