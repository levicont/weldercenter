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

    @Column(name = 'WELD_METHOD_CODE')
    String weldMethod = WeldMethodType.MAG

    @Column(name = 'ELECTRODE')
    String electrode

    @Column(name = 'WELD_WIRE')
    String weldWire

    @Column(name = 'WELD_GAS')
    String weldGas

    @ManyToOne
    PersonalProtocol personalProtocol

    WeldDetail weldDetail

    @Column(name = 'STEEL_TYPE')
    String steelType

    RadiationTest radiationTest

    VisualTest visualTest

    MechanicalTest mechanicalTest


    @ElementCollection
    @CollectionTable(name = 'weld_pattern_weld_joins')
    Set<WeldJoin> weldJoins = new HashSet<>()

    @ElementCollection
    @CollectionTable(name = 'weld_pattern_weld_positions')
    Set<WeldPosition> weldPositions = new HashSet<>()




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
