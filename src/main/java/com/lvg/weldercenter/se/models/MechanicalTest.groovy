package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.converters.LocalDateConverter

import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated
import java.time.LocalDate

@Embeddable
class MechanicalTest implements Serializable{

    @Column(name = 'MECHANICAL_TEST_PROTOCOL_NUMBER')
    String protocolNumber

    @Column(name = 'MECHANICAL_TEST_PROTOCOL_DATE')
    @Convert(converter = LocalDateConverter.class)
    LocalDate protocolDate = LocalDate.now()

    @Column(name = 'MECHANICAL_TEST_ANGLE')
    Double angle

    @Column(name = 'MECHANICAL_TEST_CLEARANCE')
    Double clearance

    @Column(name = 'MECHANICAL_TEST_EVALUATION')
    @Enumerated(EnumType.STRING)
    Evaluation evaluation = Evaluation.E

    @Override
    String toString() {
        return "Mechanical test $protocolNumber от $protocolDate $evaluation"
    }
}
