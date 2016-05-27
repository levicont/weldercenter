package com.lvg.weldercenter.ui.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Victor Levchenko LVG Corp. on 28.11.14.
 */
public class DateUtil implements Holidays{

    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
    public static final LocalDate DEFAULT_DATE = LocalDate.of(2000, Month.JANUARY, 1);
    public static final LocalDate TODAY = LocalDate.now();


    public static LocalDate getLocalDate(Date date) {
        if (date == null)
            return null;

        Instant instant = Instant.ofEpochMilli(date.getTime());
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date getDate(LocalDate localDate) {
        if (localDate == null)
            return null;
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }

    public static String format(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return DATE_FORMATTER.format(localDate);
    }

    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static boolean isHoliday(LocalDate date) {
        if (date.getDayOfWeek().equals(SATURDAY))
            return true;
        if (date.getDayOfWeek().equals(SUNDAY))
            return true;
        Holiday holiday = new Holiday(date.getDayOfMonth(),date.getMonthValue());
        if(ALL_HOLIDAYS.contains(holiday)){
            return true;
        }
         return false;
    }

    public static String formatedYearEndString(int yearCount){

        String god = "год";
        String goda = "года";
        String let = "лет";
        String value = String.valueOf(yearCount);
        if (value.equals("11")||value.equals("12")||value.equals("13")||value.equals("14"))
            return let;
        if(value.endsWith("1"))
            return god;
        if (value.endsWith("2")||value.endsWith("3")||value.endsWith("4"))
            return goda;
        return let;
    }

}

