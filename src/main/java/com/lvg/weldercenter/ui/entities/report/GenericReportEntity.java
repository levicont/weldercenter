package com.lvg.weldercenter.ui.entities.report;

/**
 * Created by Victor on 09.07.2015.
 */
public abstract class GenericReportEntity {
    protected final String SIZE_SEPARATOR = " x ";
    protected final String NULL_FIELD = "NULL";
    protected final String SPACE_SEPARATOR = " ";
    protected final String SLASH_SEPARATOR = " / ";
    protected final String SEMICOLON_SEPARATOR = "; ";
    protected final String COLON_SEPARATOR = ": ";
    protected final String YES_STRING = "есть";
    protected final String NO_STRING = "нет";
    protected final String DATE_SUFFIX = " г.";

    protected void deleteLastSeparator(StringBuilder string, String separator){
        if (string.toString().isEmpty())
            return;
        if (string.toString().endsWith(separator)){
            string.delete(string.length()-separator.length(),string.length());
        }
    }
}
