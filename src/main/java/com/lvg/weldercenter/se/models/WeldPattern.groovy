package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R

import javax.persistence.*

@Entity
@Table(name = 'weld_pattern')
class WeldPattern implements Serializable{

    @Id
    @GeneratedValue(generator = R.ModelsConfig.ID_GENERATOR_NAME)
    @Column(name = 'ID')
    Long id

    @Column(name = 'THICKNESS')
    Double thickness

    @Column(name = 'DIAMETR')
    Double diametr

    @Column(name = 'MARK')
    String mark

    @Column(name ='IS_HEATING')
    Boolean isHeating = false

    @Column(name ='IS_HEAT_TREATMENT')
    Boolean isHeatTreatment = false

    @Column(name = 'WELD_METHOD')
    String weldMethod

    @Column(name = 'ELECTRODE')
    String electrode

    @Column(name = 'WELD_WIRE')
    String weldWire

    @Column(name = 'WELD_GAS')
    String weldGas

    @ManyToOne
    PersonalProtocol personalProtocol

    @Column(name = 'WELD_DETAIL')
    String weldDetail = WeldDetail.P

    //TODO has to be @ManyToOne relationship
    @Column(name = 'STEEL_TYPE')
    String steelType

    //TODO has to be embeddable
    @Column(name = 'RADIATION_TEST')
    String radiationTest

    //TODO has to be embeddable
    @Column(name = 'VISUAL_TEST')
    String visualTest

    //TODO has to be embeddable
    @Column(name = 'MECHANICAL_TEST')
    String mechanicalTest


    //TODO has to be set
    @Column(name = 'WELD_JOIN_TYPES')
    String weldJoinTypes

    @Column(name = 'WELD_POSITIONS')
    String weldPositions




    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        WeldPattern that = (WeldPattern) o

        if (id != that.id) return false

        return true
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }

    String toString(){
        def result = "$mark"
        return result
    }
}
