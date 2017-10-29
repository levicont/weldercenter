package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.cfg.R
import com.lvg.weldercenter.se.exceptions.WelderCenterModelException

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

    @Column(name = 'DIAMETER')
    Double diameter

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

    @ManyToOne(optional = false)
    PersonalProtocol personalProtocol

    WeldDetail weldDetail

    @Column(name = 'STEEL_TYPE')
    String steelType

    RadiationTest radiationTest

    VisualTest visualTest

    MechanicalTest mechanicalTest


    @ElementCollection
    @CollectionTable(name = 'w_pattern_w_join',
            joinColumns = @JoinColumn(name = 'w_pattern_id'))
    Set<WeldJoin> weldJoins = new HashSet<>()

    @ElementCollection
    @CollectionTable(name = 'w_pattern_w_position',
            joinColumns = @JoinColumn(name = 'w_pattern_id'))
    Set<WeldPosition> weldPositions = new HashSet<>()

    protected WeldPattern() {
    }

    WeldPattern(PersonalProtocol personalProtocol){
        if (personalProtocol== null || personalProtocol.id == null)
            throw new WelderCenterModelException("PersonalProtocol id must be not null")
        this.personalProtocol = personalProtocol
    }

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
