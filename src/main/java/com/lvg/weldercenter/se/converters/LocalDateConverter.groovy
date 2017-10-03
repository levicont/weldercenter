package com.lvg.weldercenter.se.converters

import javax.persistence.AttributeConverter
import javax.persistence.Converter
import java.time.LocalDate

/**
 * Created by Victor on 03.10.2017.
 */

@Converter
class LocalDateConverter implements AttributeConverter<LocalDate, java.sql.Date>{
    @Override
    java.sql.Date convertToDatabaseColumn(LocalDate localDate) {
        return localDate == null ? null : java.sql.Date.valueOf(localDate)
    }

    @Override
    LocalDate convertToEntityAttribute(java.sql.Date dbData) {
        return dbData == null ? null : dbData.toLocalDate()
    }
}
