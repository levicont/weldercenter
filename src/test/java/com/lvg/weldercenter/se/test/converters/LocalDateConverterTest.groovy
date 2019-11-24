package com.lvg.weldercenter.se.test.converters

import com.lvg.weldercenter.se.converters.LocalDateConverter
import org.junit.Test

import java.sql.Date
import java.time.LocalDate

/**
 * Created by Victor Levchenko LVG Corp. on 08.04.19.
 */
class LocalDateConverterTest {

    @Test
    void convertToDatabaseColumnTest(){
        LocalDateConverter converter = new LocalDateConverter()
        LocalDate localDate = LocalDate.of(2000,01,25)
        Date sqlDate = converter.convertToDatabaseColumn(localDate)
        assert sqlDate.toString() == localDate.toString()
        LocalDate localDate1 = converter.convertToEntityAttribute(sqlDate)
        assert localDate1.toString() == localDate.toString()
        print("Default TimeZone =  ${TimeZone.getDefault()}")
    }
}
