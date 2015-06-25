package com.lvg.weldercenter.ui.entities.report;

import com.lvg.weldercenter.models.NDTDocument;
import com.lvg.weldercenter.ui.entities.NDTDocumentUI;
import com.lvg.weldercenter.ui.entities.PersonalProtocolUI;
import com.lvg.weldercenter.ui.entities.TotalProtocolUI;
import com.lvg.weldercenter.ui.util.DateUtil;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    private final String KEY_WELD_JOIN_TYPES = "WELD_JOIN_TYPES";


    private Map<String, Object> parameters = new HashMap<String, Object>(){{
        put(KEY_PROT_NUMBER,null);
        put(KEY_PROT_DATE,null);
        put(KEY_COMISSION_HEAD, null);
        put(KEY_COMISSION_NDT_SPEC,null);
        put(KEY_COMISSION_SAFETY_SPEC,null);
        put(KEY_COMISSION_WELD_SPEC,null);
        put(KEY_NDT_DOCUMENTS,null);
        put(KEY_WELDER_FULL_NAME,null);
        put(KEY_WELDER_BIRTHDAY,null);
        put(KEY_WELDER_DOC_NUMBER,null);
        put(KEY_WELDER_EXPERIENCE,null);
        put(KEY_WELDER_ATTEST_TYPE,null);
        put(KEY_WELD_PATTERN_MARKS,null);
        put(KEY_WELD_PATTERN_WELD_METHODS,null);
        put(KEY_WELD_PATTERN_DETAILS_TYPES,null);
        put(KEY_WELD_PATTERN_WELD_POSITIONS,null);
        put(KEY_WELD_JOIN_TYPES,null);
    }};

    public PersonalProtocolReportEntity(PersonalProtocolUI personalProtocolUI, TotalProtocolUI totalProtocolUI){
        parameters.replace(KEY_PROT_NUMBER, personalProtocolUI.getNumber());
        parameters.replace(KEY_PROT_DATE, personalProtocolUI.getDatePeriodicalCertFormat());
        if (totalProtocolUI.getCommissionCertification()!=null) {
            parameters.replace(KEY_COMISSION_HEAD, totalProtocolUI.getCommissionCertification().getHead().getFormatTeacherFullName("SUR-nn-sec"));
            parameters.replace(KEY_COMISSION_NDT_SPEC, totalProtocolUI.getCommissionCertification().getNdtSpecialist().getFormatTeacherFullName("SUR-nn-sec"));
            parameters.replace(KEY_COMISSION_SAFETY_SPEC, totalProtocolUI.getCommissionCertification().getSafetySpecialist().getFormatTeacherFullName("SUR-nn-sec"));
            parameters.replace(KEY_COMISSION_WELD_SPEC, totalProtocolUI.getCommissionCertification().getWeldSpecialist().getFormatTeacherFullName("SUR-nn-sec"));
        }
        parameters.replace(KEY_NDT_DOCUMENTS,getNdtDocs(personalProtocolUI.getNdtDocuments()));
        if (personalProtocolUI.getWelder()!=null) {
            parameters.replace(KEY_WELDER_FULL_NAME, personalProtocolUI.getWelder().getFullName());
            parameters.replace(KEY_WELDER_BIRTHDAY, personalProtocolUI.getWelder().getBirthdayFormat());
            parameters.replace(KEY_WELDER_DOC_NUMBER, personalProtocolUI.getWelder().getDocNumber());
            parameters.replace(KEY_WELDER_EXPERIENCE, getExperience(personalProtocolUI.getWelder().getDateBegin()));
            parameters.replace(KEY_WELDER_ATTEST_TYPE, null);
        }
        parameters.replace(KEY_WELD_PATTERN_MARKS,null);
        parameters.replace(KEY_WELD_PATTERN_WELD_METHODS,null);
        parameters.replace(KEY_WELD_PATTERN_DETAILS_TYPES,null);
        parameters.replace(KEY_WELD_PATTERN_WELD_POSITIONS,null);
        parameters.replace(KEY_WELD_JOIN_TYPES,null);
    }

    private String getNdtDocs(List<NDTDocumentUI> ndtDocumentUIList){
        if (ndtDocumentUIList==null)
            return "null";
        StringBuilder result = new StringBuilder();
        for (NDTDocumentUI ndt : ndtDocumentUIList){
            result.append(ndt.getName());
            if (ndtDocumentUIList.iterator().hasNext())
                result.append("; ");
        }
        return result.toString();
    }

    private String getExperience(Date dateBegin){
        if (dateBegin==null)
            return "null";
        LocalDate begin = DateUtil.getLocalDate(dateBegin);
        return LocalDate.now().getYear()-begin.getYear()+"";
    }


}
