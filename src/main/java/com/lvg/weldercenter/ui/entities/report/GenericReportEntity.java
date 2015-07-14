package com.lvg.weldercenter.ui.entities.report;

import org.apache.log4j.Logger;

/**
 * Created by Victor on 09.07.2015.
 */
public abstract class GenericReportEntity {
    protected final Logger LOGGER = Logger.getLogger(GenericReportEntity.class);
    protected final String SIZE_SEPARATOR = " x ";
    protected final String NULL_FIELD = "NULL";
    protected final String SPACE_SEPARATOR = " ";
    protected final String SLASH_SEPARATOR = " / ";
    protected final String SEMICOLON_SEPARATOR = "; ";
    protected final String COLON_SEPARATOR = ": ";
    protected final String YES_STRING = "есть";
    protected final String NO_STRING = "нет";
    protected final String DATE_SUFFIX = " г.";
    protected final String FLOAT_DIGIT_SUFFIX = ".0";

    //NDT Laboratory properties
    protected final String HEAD_OF_NDT_LABORATORY = "В.Г. Левченко";
    protected final String RT_CERT_OF_HEAD_LABORATORY = "(II уровень RT, квалификационное удостоверение №30212)";
    protected final String RT_NDT_SPEC = "Д.А. Крапива";
    protected final String RT_CERT_OF_NDT_SPEC = "(III уровень RT, квалификационное удостоверение №30298)";
    protected final String MECH_TEST_SPEC = "Д.А. Крапива";

    protected void deleteLastSeparator(StringBuilder string, String separator){
        if (string.toString().isEmpty())
            return;
        if (string.toString().endsWith(separator)){
            string.delete(string.length()-separator.length(),string.length());
        }
    }

    public String deleteFloatZeroSuffix(String string){
        if (string.isEmpty())
            return string;
        if (string.endsWith(FLOAT_DIGIT_SUFFIX)){
            return string.substring(0,string.lastIndexOf(FLOAT_DIGIT_SUFFIX));
        }
        return string;
    }
}
