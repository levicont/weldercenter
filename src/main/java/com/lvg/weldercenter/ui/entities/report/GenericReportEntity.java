package com.lvg.weldercenter.ui.entities.report;

import com.lvg.weldercenter.config.R;
import org.apache.log4j.Logger;

/**
 * Created by Victor on 09.07.2015.
 */
public abstract class GenericReportEntity {
    protected final Logger LOGGER = Logger.getLogger(GenericReportEntity.class);
    protected R.UI.Entities.Reports constants = null;

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
        if (string.endsWith(constants.GENERIC_FLOAT_DIGIT_SUFFIX)){
            return string.substring(0,string.lastIndexOf(constants.GENERIC_FLOAT_DIGIT_SUFFIX));
        }
        return string;
    }
}
