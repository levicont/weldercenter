package com.lvg.weldercenter.ui.entities.report;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Victor on 05.06.2015.
 */
public class PersonalProtocolReportEntity {
    private final String KEY_PROT_NUMBER = "PROT_NUMBER";
    private final String KEY_PROT_DATE = "PROT_DATE";
    private final String KEY_COMISSION_HEAD = "COMISSION_HEAD";
    private final String KEY_COMISSION_WELD_SPEC = "COMISSION_WELD_SPEC";
    private final String KEY_COMISSION_NDT_SPEC = "COMISSION_NDT_SPEC";
    private final String KEY_COMISSION_SAFETY_SPEC = "COMISSION_SAFETY_SPEC";
    private final String KEY_NDT_DOCUMENTS = "NDT_DOCUMENTS";
    private final String KEY_WELDER_FULL_NAME = "WELDER_FULL_NAME";
    private final String KEY_WELDER_BIRTHDAY = "WELDER_BIRTHDAY";
    private final String KEY_WELDER_DOC_NUMBER = "WELDER_DOC_NUMBER";
    private final String KEY_WELDER_EXPERIENCE = "WELDER_EXPERIENCE";
    private final String KEY_WELDER_ATTEST_TYPE = "WELDER_ATTEST_TYPE";
    private final String KEY_WELD_PATTERN_MARKS = "WELD_PATTERN_MARKS";
    private final String KEY_WELD_PATTERN_WELD_METHODS = "WELD_PATTERN_WELD_METHODS";
    private final String KEY_WELD_PATTERN_DETAILS_TYPES = "WELD_PATTERN_DETAILS_TYPES";
    private final String KEY_WELD_PATTERN_WELD_POSITIONS = "WELD_PATTERN_WELD_POSITIONS";


    private Map<String, Object> parameters = new HashMap<String, Object>(){{
        put(KEY_PROT_NUMBER,null);

    }};


}
