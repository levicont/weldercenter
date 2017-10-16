package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.converters.LocalDateConverter

import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.Size
import java.time.LocalDate

@Embeddable
class RadiationTest implements Serializable{

    @Column(name = 'RT_PROTOCOL_NUMBER')
    String protocolNumber

    @Column(name = 'RT_PROTOCOL_DATE')
    @Convert(converter = LocalDateConverter.class)
    LocalDate protocolDate = LocalDate.now()

    @Column(name = 'RT_SENSITIVITY')
    @Size(min = 0)
    Double sensitivity

    @Column(name = 'RT_DEFECTS')
    String defects

    @Column(name = 'RT_EVALUATION')
    @Enumerated(EnumType.STRING)
    Evaluation evaluation = Evaluation.E

    @Override
    String toString() {
        return "RT $protocolNumber от $protocolDate $evaluation"
    }
}
