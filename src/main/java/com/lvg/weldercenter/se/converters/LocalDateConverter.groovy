package com.lvg.weldercenter.se.converters

import javax.persistence.AttributeConverter
import java.sql.Date
import java.time.LocalDate
import java.time.ZoneId


class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    private final ZoneId DEFAULT_TIME_ZONE_ID = ZoneId.of('UTC')

    @Override
    Date convertToDatabaseColumn(LocalDate attribute) {
        long date = attribute.atStartOfDay(DEFAULT_TIME_ZONE_ID).toInstant().toEpochMilli()
        Date result = new Date(date)
        return result
    }

    @Override
    LocalDate convertToEntityAttribute(Date dbData) {
        return dbData.toLocalDate()
    }
}
