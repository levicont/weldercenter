package com.lvg.weldercenter.ui.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.zip.DataFormatException;

/**
 * Created by Victor Levchenko LVG Corp. on 28.11.14.
 */
public class DateUtil {

    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
    public static final LocalDate DEFAULT_DATE = LocalDate.of(2000, Month.JANUARY,1);

    public static LocalDate getLocalDate(Date date){
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date getDate(LocalDate localDate){
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }

    public static String format(LocalDate localDate){
        if(localDate == null){
            return null;
        }
        return DATE_FORMATTER.format(localDate);
    }

    public static String format(Date date){
        if (date == null){
            return null;
        }
        return SIMPLE_DATE_FORMAT.format(date);
    }

    /*public static LocalDate parse(String date){
        try{
            return null;//DATE_FORMATTER.parse(date);
        }catch (DataFormatException ex){
            return null;
        }

    }

    public static boolean isCorrectDate(String dateString){
        return DateUtil.parse(dateString) != null;
    }*/
}
