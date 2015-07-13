package com.lvg.weldercenter.ui.entities.report;

import com.lvg.weldercenter.ui.entities.TotalProtocolUI;
import com.lvg.weldercenter.ui.util.DateUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Victor on 10.07.2015.
 */
public class TotalProtocolReportEntity extends GenericReportEntity {
    private final String KEY_ID = "ID";
    private final String KEY_NUMBER = "NUMBER";
    private final String KEY_DATE_CERT = "DATE_CERT";
    private final String KEY_NEXT_DATE_CERT = "NEXT_DATE_CERT";
    private final String KEY_DATE_CERT_FORMAT = "DATE_CERT_FORMAT";
    private final String KEY_COMMISSION_CERTIFICATION_HEAD = "COMMISSION_CERTIFICATION_HEAD";
    private final String KEY_COMMISSION_CERTIFICATION_WELD_SPEC = "COMMISSION_CERTIFICATION_WELD_SPEC";
    private final String KEY_COMMISSION_CERTIFICATION_NDT_SPEC = "COMMISSION_CERTIFICATION_NDT_SPEC";
    private final String KEY_COMMISSION_CERTIFICATION_SAFETY_SPEC = "COMMISSION_CERTIFICATION_SAFETY_SPEC";
    private final String KEY_COMMISSION_CERTIFICATION_HEAD_INV = "COMMISSION_CERTIFICATION_HEAD_INV";
    private final String KEY_COMMISSION_CERTIFICATION_WELD_SPEC_INV = "COMMISSION_CERTIFICATION_WELD_SPEC_INV";
    private final String KEY_COMMISSION_CERTIFICATION_NDT_SPEC_INV = "COMMISSION_CERTIFICATION_NDT_SPEC_INV";
    private final String KEY_COMMISSION_CERTIFICATION_SAFETY_SPEC_INV = "COMMISSION_CERTIFICATION_SAFETY_SPEC_INV";
    private final String KEY_HEAD_OF_NDT_LABORATORY = "HEAD_OF_NDT_LABORATORY";
    private final String KEY_RT_CERT_OF_HEAD_LABORATORY = "RT_CERT_OF_HEAD_LABORATORY";
    private final String KEY_RT_SPEC = "RT_SPEC";
    private final String KEY_RT_SPEC_CERT_NUMBER = "RT_SPEC_CERT_NUMBER";
    private final String KEY_MECH_TEST_SPEC = "MECH_TEST_SPEC";

    private final Map<String, Object> parameters = new HashMap<String, Object>(){{
        put(KEY_ID,null);
        put(KEY_NUMBER,null);
        put(KEY_DATE_CERT, null);
        put(KEY_NEXT_DATE_CERT, null);
        put(KEY_DATE_CERT_FORMAT, null);
        put(KEY_COMMISSION_CERTIFICATION_HEAD, null);
        put(KEY_COMMISSION_CERTIFICATION_WELD_SPEC, null);
        put(KEY_COMMISSION_CERTIFICATION_NDT_SPEC, null);
        put(KEY_COMMISSION_CERTIFICATION_SAFETY_SPEC, null);
        put(KEY_COMMISSION_CERTIFICATION_HEAD_INV, null);
        put(KEY_COMMISSION_CERTIFICATION_WELD_SPEC_INV, null);
        put(KEY_COMMISSION_CERTIFICATION_NDT_SPEC_INV, null);
        put(KEY_COMMISSION_CERTIFICATION_SAFETY_SPEC_INV, null);
        put(KEY_HEAD_OF_NDT_LABORATORY, HEAD_OF_NDT_LABORATORY);
        put(KEY_RT_CERT_OF_HEAD_LABORATORY, RT_CERT_OF_HEAD_LABORATORY);
        put(KEY_RT_SPEC, RT_NDT_SPEC);
        put(KEY_RT_SPEC_CERT_NUMBER, RT_CERT_OF_NDT_SPEC);
        put(KEY_MECH_TEST_SPEC, MECH_TEST_SPEC);
    }};

    public TotalProtocolReportEntity(TotalProtocolUI totalProtocolUI){
        fillParameters(totalProtocolUI);
    }

    private void fillParameters(TotalProtocolUI totalProtocolUI){
        parameters.replace(KEY_ID,totalProtocolUI.getId());
        parameters.replace(KEY_NUMBER, totalProtocolUI.getNumber());
        parameters.replace(KEY_DATE_CERT, totalProtocolUI.getDateCert());
        parameters.replace(KEY_NEXT_DATE_CERT, totalProtocolUI.getDateCert()!=null?
                DateUtil.format(DateUtil.getLocalDate(totalProtocolUI.getDateCert()).plusYears(2))+DATE_SUFFIX:NULL_FIELD);
        parameters.replace(KEY_DATE_CERT_FORMAT, totalProtocolUI.getDateCertFormat()+DATE_SUFFIX);
        if(totalProtocolUI.getCommissionCertification()!=null) {
            parameters.replace(KEY_COMMISSION_CERTIFICATION_HEAD,
                    totalProtocolUI.getCommissionCertification().getHead() != null ?
                            totalProtocolUI.getCommissionCertification().getHead().getFormatTeacherFullName("SUR-nn-sec") : NULL_FIELD);
            parameters.replace(KEY_COMMISSION_CERTIFICATION_NDT_SPEC,
                    totalProtocolUI.getCommissionCertification().getNdtSpecialist() != null ?
                            totalProtocolUI.getCommissionCertification().getNdtSpecialist().getFormatTeacherFullName("SUR-nn-sec") : NULL_FIELD);
            parameters.replace(KEY_COMMISSION_CERTIFICATION_WELD_SPEC,
                    totalProtocolUI.getCommissionCertification().getWeldSpecialist() != null ?
                            totalProtocolUI.getCommissionCertification().getWeldSpecialist().getFormatTeacherFullName("SUR-nn-sec") : NULL_FIELD);
            parameters.replace(KEY_COMMISSION_CERTIFICATION_SAFETY_SPEC,
                    totalProtocolUI.getCommissionCertification().getSafetySpecialist() != null ?
                            totalProtocolUI.getCommissionCertification().getSafetySpecialist().getFormatTeacherFullName("SUR-nn-sec") : NULL_FIELD);
            parameters.replace(KEY_COMMISSION_CERTIFICATION_HEAD_INV,
                    totalProtocolUI.getCommissionCertification().getHead() != null ?
                            totalProtocolUI.getCommissionCertification().getHead().getFormatTeacherFullName("nn-sec-SUR") : NULL_FIELD);
            parameters.replace(KEY_COMMISSION_CERTIFICATION_NDT_SPEC_INV,
                    totalProtocolUI.getCommissionCertification().getNdtSpecialist() != null ?
                            totalProtocolUI.getCommissionCertification().getNdtSpecialist().getFormatTeacherFullName("nn-sec-SUR") : NULL_FIELD);
            parameters.replace(KEY_COMMISSION_CERTIFICATION_WELD_SPEC_INV,
                    totalProtocolUI.getCommissionCertification().getWeldSpecialist() != null ?
                            totalProtocolUI.getCommissionCertification().getWeldSpecialist().getFormatTeacherFullName("nn-sec-SUR") : NULL_FIELD);
            parameters.replace(KEY_COMMISSION_CERTIFICATION_SAFETY_SPEC_INV,
                    totalProtocolUI.getCommissionCertification().getSafetySpecialist() != null ?
                            totalProtocolUI.getCommissionCertification().getSafetySpecialist().getFormatTeacherFullName("nn-sec-SUR") : NULL_FIELD);
        }
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
