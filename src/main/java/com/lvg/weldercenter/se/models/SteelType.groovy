package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'steel_type')
class SteelType implements Serializable{
    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name = 'ID')
    Long id

    @Column(name = 'TYPE')
    String type

    @Column(name = 'STEEL_GROUP')
    @Enumerated(EnumType.STRING)
    SteelGroup steelGroup = SteelGroup.W01

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof SteelType)) return false

        SteelType steelType = (SteelType) o

        if (id != steelType.id) return false
        if (type != steelType.type) return false

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
