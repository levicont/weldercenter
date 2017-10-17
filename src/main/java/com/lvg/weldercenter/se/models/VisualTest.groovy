package com.lvg.weldercenter.se.models

import com.lvg.weldercenter.se.converters.LocalDateConverter

import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated
import java.time.LocalDate

@Embeddable
class VisualTest implements Serializable{

    @Column(name = 'VT_PROTOCOL_NUMBER')
    String protocolNumber

    @Column(name = 'VT_PROTOCOL_DATE')
    @Convert(converter = LocalDateConverter.class)
    LocalDate protocolDate = LocalDate.now()

    @Column(name = 'VT_DEFECTS')
    String defects

    @Column(name = 'VT_EVALUATION')
    @Enumerated(EnumType.STRING)
    Evaluation evaluation = Evaluation.E

    @Override
    String toString() {
        return "VT $protocolNumber от $protocolDate $evaluation"
    }
}
