package com.lvg.weldercenter.ui.entities.report;

import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.util.DateUtil;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by Victor on 05.06.2015.
 */
public class PersonalProtocolReportEntity extends GenericReportEntity{
    private final String KEY_PROT_NUMBER = "PROT_NUMBER";
    private final String KEY_PROT_DATE = "PROT_DATE";
    private final String KEY_TOTAL_PROT_NUMBER = "TOTAL_PROT_NUMBER";
    private final String KEY_TOTAL_PROT_DATE = "TOTAL_PROT_DATE";
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
    private final String KEY_WELD_PATTERN_HEATING = "WELD_PATTERN_HEATING";
    private final String KEY_WELD_PATTERN_HEAT_TREATMENT = "WELD_PATTERN_HEAT_TREATMENT";
    private final String KEY_WELD_PATTERN_STEEL_TYPE = "WELD_PATTERN_STEEL_TYPE";
    private final String KEY_WELD_PATTERN_THICKNESS = "WELD_PATTERN_THICKNESS";
    private final String KEY_WELD_PATTERN_DIAMETER = "WELD_PATTERN_DIAMETER";
    private final String KEY_WELD_PATTERN_ELECTRODE_WIRE = "WELD_PATTERN_ELECTRODE_WIRE";
    private final String KEY_WELD_PATTERN_GAS = "WELD_PATTERN_GAS";
    private final String KEY_WELD_PATTERN_VT_NUMBERS = "WELD_PATTERN_VT_NUMBERS";
    private final String KEY_WELD_PATTERN_VT_DATE = "WELD_PATTERN_VT_DATE";
    private final String KEY_WELD_PATTERN_VT_EVALUATION = "WELD_PATTERN_VT_EVALUATION";
    private final String KEY_WELD_PATTERN_RT_NUMBERS = "WELD_PATTERN_RT_NUMBERS";
    private final String KEY_WELD_PATTERN_RT_DATE = "WELD_PATTERN_RT_DATE";
    private final String KEY_WELD_PATTERN_RT_EVALUATION = "WELD_PATTERN_RT_EVALUATION";
    private final String KEY_WELD_PATTERN_MT_NUMBERS = "WELD_PATTERN_MT_NUMBERS";
    private final String KEY_WELD_PATTERN_MT_DATE = "WELD_PATTERN_MT_DATE";
    private final String KEY_WELD_PATTERN_MT_EVALUATION = "WELD_PATTERN_MT_EVALUATION";
    private final String KEY_THEORY_EVALUATION = "THEORY_EVALUATION";
    private final String KEY_RESOLUTION_CERT = "RESOLUTION_CERT";
    private final String KEY_PERIOD_DATE_CERT = "PERIOD_DATE_CERT";





    private final int PERIOD_CERTIFICATION = 2;

    private String fio = NULL_FIELD;
    private String mark = NULL_FIELD;
    private String ndtDocs = NULL_FIELD;
    private List<WeldPatternReportEntity> patterns = new ArrayList<WeldPatternReportEntity>();



    private Map<String, Object> parameters = new HashMap<String, Object>(){{
        put(KEY_PROT_NUMBER,null);
        put(KEY_PROT_DATE,null);
        put(KEY_TOTAL_PROT_NUMBER,null);
        put(KEY_TOTAL_PROT_DATE,null);
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
        put(KEY_WELD_PATTERN_HEATING,null);
        put(KEY_WELD_PATTERN_HEAT_TREATMENT,null);
        put(KEY_WELD_PATTERN_STEEL_TYPE,null);
        put(KEY_WELD_PATTERN_THICKNESS,null);
        put(KEY_WELD_PATTERN_DIAMETER,null);
        put(KEY_WELD_PATTERN_ELECTRODE_WIRE,null);
        put(KEY_WELD_PATTERN_GAS,null);
        put(KEY_WELD_PATTERN_VT_NUMBERS,null);
        put(KEY_WELD_PATTERN_VT_DATE,null);
        put(KEY_WELD_PATTERN_VT_EVALUATION,null);
        put(KEY_WELD_PATTERN_RT_NUMBERS,null);
        put(KEY_WELD_PATTERN_RT_DATE,null);
        put(KEY_WELD_PATTERN_RT_EVALUATION,null);
        put(KEY_WELD_PATTERN_MT_NUMBERS,null);
        put(KEY_WELD_PATTERN_MT_DATE,null);
        put(KEY_WELD_PATTERN_MT_EVALUATION,null);
        put(KEY_THEORY_EVALUATION,null);
        put(KEY_RESOLUTION_CERT,null);
        put(KEY_PERIOD_DATE_CERT,null);
    }};

    public PersonalProtocolReportEntity(PersonalProtocolUI personalProtocolUI, TotalProtocolUI totalProtocolUI){

        parameters.replace(KEY_PROT_NUMBER, personalProtocolUI.getNumber());
        parameters.replace(KEY_PROT_DATE, personalProtocolUI.getDatePeriodicalCertFormat()+DATE_SUFFIX);
        parameters.replace(KEY_PERIOD_DATE_CERT,getNextDateCertification(personalProtocolUI.getDatePeriodicalCert()));
        parameters.replace(KEY_TOTAL_PROT_NUMBER, totalProtocolUI.getNumber());
        parameters.replace(KEY_TOTAL_PROT_DATE, totalProtocolUI.getDateCertFormat());
        if (totalProtocolUI.getCommissionCertification()!=null) {
            parameters.replace(KEY_COMISSION_HEAD, totalProtocolUI.getCommissionCertification().getHead().getFormatTeacherFullName("SUR-nn-sec"));
            parameters.replace(KEY_COMISSION_NDT_SPEC, totalProtocolUI.getCommissionCertification().getNdtSpecialist().getFormatTeacherFullName("SUR-nn-sec"));
            parameters.replace(KEY_COMISSION_SAFETY_SPEC, totalProtocolUI.getCommissionCertification().getSafetySpecialist().getFormatTeacherFullName("SUR-nn-sec"));
            parameters.replace(KEY_COMISSION_WELD_SPEC, totalProtocolUI.getCommissionCertification().getWeldSpecialist().getFormatTeacherFullName("SUR-nn-sec"));
        }
        parameters.replace(KEY_NDT_DOCUMENTS,getNdtDocs(personalProtocolUI.getNdtDocuments()));
        this.ndtDocs = (String)parameters.get(KEY_NDT_DOCUMENTS);
        if (personalProtocolUI.getWelder()!=null) {
            this.fio = personalProtocolUI.getWelder().getFormatName("SUR-nn-sec");
            parameters.replace(KEY_WELDER_FULL_NAME, personalProtocolUI.getWelder().getFormatName("SUR-NN-SEC"));
            parameters.replace(KEY_WELDER_BIRTHDAY, personalProtocolUI.getWelder().getBirthdayFormat()+DATE_SUFFIX);
            parameters.replace(KEY_WELDER_DOC_NUMBER, personalProtocolUI.getWelder().getDocNumber());
            parameters.replace(KEY_WELDER_EXPERIENCE, getExperience(personalProtocolUI.getWelder().getDateBegin()));
            parameters.replace(KEY_WELDER_ATTEST_TYPE, personalProtocolUI.getAttestType());
        }
        if (personalProtocolUI.getWeldPatterns()!=null && !personalProtocolUI.getWeldPatterns().isEmpty()) {
            List<WeldPatternUI> weldPatterns = personalProtocolUI.getWeldPatterns();
            this.patterns = getPatternsReportEntities(weldPatterns);
            parameters.replace(KEY_WELD_PATTERN_MARKS, getWeldPatternsMarks(weldPatterns));
            parameters.replace(KEY_WELD_PATTERN_WELD_METHODS, getWeldPatternWeldMethods(weldPatterns));
            parameters.replace(KEY_WELD_PATTERN_DETAILS_TYPES, getWeldPatternDetailTypes(weldPatterns));
            parameters.replace(KEY_WELD_PATTERN_WELD_POSITIONS, getWeldPatternWeldPositions(weldPatterns));
            parameters.replace(KEY_WELD_JOIN_TYPES,getWeldPatternWeldJoinTypes(weldPatterns));
            parameters.replace(KEY_WELD_PATTERN_HEATING, getWeldPatternHeating(weldPatterns));
            parameters.replace(KEY_WELD_PATTERN_HEAT_TREATMENT, getWeldPatternHeatTreatment(weldPatterns));
            parameters.replace(KEY_WELD_PATTERN_STEEL_TYPE, getWeldPatternSteelTypes(weldPatterns));
            parameters.replace(KEY_WELD_PATTERN_THICKNESS, getWeldPatternThicknesses(weldPatterns));
            parameters.replace(KEY_WELD_PATTERN_DIAMETER, getWeldPatternDiameters(weldPatterns));
            parameters.replace(KEY_WELD_PATTERN_ELECTRODE_WIRE, getWeldPatternElectrodeWire(weldPatterns));
            parameters.replace(KEY_WELD_PATTERN_GAS, getWeldPatternGas(weldPatterns));
            Map<String, String> vtParameters = getWeldPatternVTParameters(weldPatterns);
            Map<String, String> rtParameters = getWeldPatternRTParameters(weldPatterns);
            Map<String, String> mtParameters = getWeldPatternMTParameters(weldPatterns);
            parameters.replace(KEY_WELD_PATTERN_VT_NUMBERS,vtParameters.get(KEY_WELD_PATTERN_VT_NUMBERS));
            parameters.replace(KEY_WELD_PATTERN_VT_DATE,vtParameters.get(KEY_WELD_PATTERN_VT_DATE));
            parameters.replace(KEY_WELD_PATTERN_VT_EVALUATION,vtParameters.get(KEY_WELD_PATTERN_VT_EVALUATION));

            parameters.replace(KEY_WELD_PATTERN_RT_NUMBERS,rtParameters.get(KEY_WELD_PATTERN_RT_NUMBERS));
            parameters.replace(KEY_WELD_PATTERN_RT_DATE,rtParameters.get(KEY_WELD_PATTERN_RT_DATE));
            parameters.replace(KEY_WELD_PATTERN_RT_EVALUATION,rtParameters.get(KEY_WELD_PATTERN_RT_EVALUATION));

            parameters.replace(KEY_WELD_PATTERN_MT_NUMBERS,mtParameters.get(KEY_WELD_PATTERN_MT_NUMBERS));
            parameters.replace(KEY_WELD_PATTERN_MT_DATE,mtParameters.get(KEY_WELD_PATTERN_MT_DATE));
            parameters.replace(KEY_WELD_PATTERN_MT_EVALUATION,mtParameters.get(KEY_WELD_PATTERN_MT_EVALUATION));

        }
        if (personalProtocolUI.getTheoryTest()!=null){
            parameters.replace(KEY_THEORY_EVALUATION, personalProtocolUI.getTheoryTest().getRating());
        }
        if (personalProtocolUI.getResolutionCertification()!=null){
            parameters.replace(KEY_RESOLUTION_CERT, personalProtocolUI.getResolutionCertification().getTextResolution());
        }


    }

    private String getNdtDocs(List<NDTDocumentUI> ndtDocumentUIList){
        if (ndtDocumentUIList==null)
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        for (NDTDocumentUI ndt : ndtDocumentUIList){
            result.append(ndt.getName());
            result.append("; ");
        }
        deleteLastSeparator(result,SEMICOLON_SEPARATOR);
        return result.toString();
    }

    private String getExperience(Date dateBegin){
        if (dateBegin==null)
            return NULL_FIELD;
        LocalDate begin = DateUtil.getLocalDate(dateBegin);
        int yearsCount = LocalDate.now().getYear()-begin.getYear();
        return yearsCount+" "+DateUtil.formatedYearEndString(yearsCount);
    }

    private String getWeldPatternsMarks(List<WeldPatternUI> weldPatterns){
        if (weldPatterns==null || weldPatterns.isEmpty())
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp: weldPatterns){
            result.append(wp.getMark());
            result.append(SLASH_SEPARATOR);
        }
        deleteLastSeparator(result, SLASH_SEPARATOR);
        return result.toString();
    }

    private String getWeldPatternWeldMethods(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp : weldPatterns){
            if (wp.getWeldMethod()!=null)
                result.append(wp.getWeldMethod().getNameCode());
            else
                result.append(NULL_FIELD);
            result.append(SEMICOLON_SEPARATOR);
        }
        deleteLastSeparator(result, SEMICOLON_SEPARATOR);
        return result.toString();
    }

    private String getWeldPatternDetailTypes(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp : weldPatterns){
            if (wp.getWeldDetail()!=null) {
                String code = wp.getWeldDetail().getCode();
                String describe = wp.getWeldDetail().getType();
                if (describe!=null)
                    describe=describe.toLowerCase();
                result.append(code + " (" + describe + ")");
            }
            else
                result.append(NULL_FIELD);
            result.append(SEMICOLON_SEPARATOR);
        }
        deleteLastSeparator(result, SEMICOLON_SEPARATOR);
        return result.toString();
    }

    private String getWeldPatternWeldPositions(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp : weldPatterns){
            if (wp.getWeldPositions()==null || wp.getWeldPositions().isEmpty())
                continue;
            List<WeldPositionUI> weldPositions = wp.getWeldPositions();
            for (WeldPositionUI position : weldPositions){
                if (!result.toString().contains(position.getCode())) {
                    result.append(position.getCode());
                    if (weldPositions.iterator().hasNext())
                        result.append(" ");
                }
            }
        }
        return result.toString();

    }

    private String getWeldPatternWeldJoinTypes(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp : weldPatterns){
            List<WeldJoinTypeUI> joinTypes = wp.getWeldJoinTypes();
            if (joinTypes==null || joinTypes.isEmpty())
                continue;
            for (WeldJoinTypeUI wjt : joinTypes){
                if (!result.toString().contains(wjt.getType())){
                    result.append(wjt.getType());
                    if (joinTypes.iterator().hasNext()){
                        result.append(" ");
                    }
                }
            }
        }
        return result.toString();
    }

    private String getWeldPatternHeating(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        List<String> heatings = new ArrayList<String>();
        for (WeldPatternUI wp : weldPatterns){
            if (wp.getIsHeating())
                heatings.add(YES_STRING);
            else
                heatings.add(NO_STRING);
        }
        if (!heatings.contains(YES_STRING))
            return NO_STRING;
        if (!heatings.contains(NO_STRING))
            return YES_STRING;
        for (String heating: heatings){
            result.append(heating);
            if (heatings.iterator().hasNext())
                result.append("; ");
        }
        return result.toString();

    }

    private String getWeldPatternHeatTreatment(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        List<String> heatTreatmentList = new ArrayList<String>();
        for (WeldPatternUI wp : weldPatterns){
            if (wp.getIsHeatTreatment())
                heatTreatmentList.add(YES_STRING);
            else
                heatTreatmentList.add(NO_STRING);
        }
        if (!heatTreatmentList.contains(YES_STRING))
            return NO_STRING;
        if (!heatTreatmentList.contains(NO_STRING))
            return YES_STRING;
        for (String heatTreatment: heatTreatmentList){
            result.append(heatTreatment);
            if (heatTreatmentList.iterator().hasNext())
                result.append("; ");
        }
        return result.toString();
    }

    private String getWeldPatternSteelTypes(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        Map<String,List<String>> steelGroupsMap = new HashMap<String, List<String>>();
        List<String> steelTypesWithoutGroup = new ArrayList<String>();
        for (WeldPatternUI wp : weldPatterns){
            SteelTypeUI steelTypeUI = wp.getSteelType();
            String steelType = steelTypeUI.getType();
            if (steelTypeUI==null)
                continue;
            SteelGroupUI steelGroupUI = steelTypeUI.getSteelGroupUI();
            if (steelGroupUI==null) {
                if (!steelTypesWithoutGroup.contains(steelType))
                    steelTypesWithoutGroup.add(steelType);
                continue;
            }
            String steelGroup = steelGroupUI.getGroup();

            if (steelGroupsMap.containsKey(steelGroup)){
                if (!steelGroupsMap.get(steelGroup).contains(steelType))
                    steelGroupsMap.get(steelGroup).add(steelType);
            }else {
                List<String> steelTypesWithGroup = new ArrayList<String>();
                steelTypesWithGroup.add(steelType);
                steelGroupsMap.put(steelGroup,steelTypesWithGroup);
            }
        }
        for (String group: steelGroupsMap.keySet()){
            result.append(group+ COLON_SEPARATOR);
            for (String type: steelGroupsMap.get(group)){
                result.append(type+ SEMICOLON_SEPARATOR);
            }
        }
        for (String type: steelTypesWithoutGroup){
            result.append(type+ SEMICOLON_SEPARATOR);
        }
        deleteLastSeparator(result, SEMICOLON_SEPARATOR);
        return result.toString();
    }

    private String getWeldPatternThicknesses(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp : weldPatterns){
            if (wp.getThickness()!= 0){
                if (!result.toString().contains(wp.getThickness()+"")){
                    result.append(wp.getThickness());
                    result.append(SEMICOLON_SEPARATOR);
                }
            }
        }
        deleteLastSeparator(result, SEMICOLON_SEPARATOR);
        return result.toString();
    }

    private String getWeldPatternDiameters(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp : weldPatterns){
            if (wp.getDiameter()!= 0){
                if (!result.toString().contains(wp.getDiameter()+"")){
                    result.append(wp.getDiameter());
                    result.append(SEMICOLON_SEPARATOR);
                }
            }
        }
        deleteLastSeparator(result, SEMICOLON_SEPARATOR);
        return result.toString();
    }

    private String getWeldPatternElectrodeWire(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        List<String> electrodes = new ArrayList<String>();
        List<String> weldWires = new ArrayList<String>();

        for (WeldPatternUI wp : weldPatterns){
            ElectrodeUI electrode = wp.getElectrode();
            if (electrode != null){
                if (!electrodes.contains(electrode.getType())) {
                    electrodes.add(electrode.getType());
                }
            }
            WeldWireUI weldWire = wp.getWeldWire();
            if (weldWire!=null){
                if (!weldWires.contains(weldWire.getType()))
                    weldWires.add(weldWire.getType());
            }
        }
        for (String el : electrodes){
            result.append(el);
            result.append(SEMICOLON_SEPARATOR);
        }
        for (String ww: weldWires){
            result.append(ww);
            result.append(SEMICOLON_SEPARATOR);
        }
        deleteLastSeparator(result, SEMICOLON_SEPARATOR);
        return result.toString();

    }

    private String getWeldPatternGas(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp : weldPatterns){
            WeldGasUI weldGas = wp.getWeldGas();
            if (weldGas!=null){
                if (!result.toString().contains(weldGas.getType())){
                    result.append(weldGas.getType());
                    result.append(SEMICOLON_SEPARATOR);
                }

            }
        }
        deleteLastSeparator(result, SEMICOLON_SEPARATOR);
        if (result.toString().isEmpty())
            return NO_STRING;
        return result.toString();
    }

    private Map<String,String> getWeldPatternVTParameters(List<WeldPatternUI> weldPatterns){
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(KEY_WELD_PATTERN_VT_NUMBERS, "");
        parameters.put(KEY_WELD_PATTERN_VT_DATE, "");
        parameters.put(KEY_WELD_PATTERN_VT_EVALUATION, "");
        if (weldPatterns == null || weldPatterns.isEmpty())
            return parameters;
        List<String> numberList = new ArrayList<String>();
        List<String> dateList = new ArrayList<String>();
        List<String> evaluationList = new ArrayList<String>();

        for (WeldPatternUI wp : weldPatterns){
            VisualTestUI vt = wp.getVisualTest();
            if (vt==null)
                continue;
            EvaluationUI vtEvaluation = vt.getEvaluation();
            if (!numberList.contains(vt.getNumber())){
                numberList.add(vt.getNumber());
            }
            if (!dateList.contains(vt.getDateFormat())){
                dateList.add(vt.getDateFormat()+DATE_SUFFIX);
            }
            if (vtEvaluation==null)
                continue;
            if (!evaluationList.contains(vtEvaluation.getType())){
                evaluationList.add(vtEvaluation.getType());
            }
        }

        StringBuilder numbers = new StringBuilder();
        StringBuilder dates = new StringBuilder();
        StringBuilder evaluations = new StringBuilder();

        for (String num : numberList){
            numbers.append(num);
            numbers.append(SEMICOLON_SEPARATOR);
        }
        for (String date: dateList){
            dates.append(date);
            dates.append(SEMICOLON_SEPARATOR);
        }
        for (String evaluation : evaluationList){
            evaluations.append(evaluation);
            evaluations.append(SEMICOLON_SEPARATOR);
        }
        deleteLastSeparator(numbers, SEMICOLON_SEPARATOR);
        deleteLastSeparator(dates, SEMICOLON_SEPARATOR);
        deleteLastSeparator(evaluations, SEMICOLON_SEPARATOR);

        parameters.replace(KEY_WELD_PATTERN_VT_NUMBERS, numbers.toString());
        parameters.replace(KEY_WELD_PATTERN_VT_DATE, dates.toString());
        parameters.replace(KEY_WELD_PATTERN_VT_EVALUATION, evaluations.toString());
        return parameters;
    }

    private Map<String, String> getWeldPatternRTParameters(List<WeldPatternUI> weldPatterns){
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(KEY_WELD_PATTERN_RT_NUMBERS, "");
        parameters.put(KEY_WELD_PATTERN_RT_DATE, "");
        parameters.put(KEY_WELD_PATTERN_RT_EVALUATION, "");
        if (weldPatterns == null || weldPatterns.isEmpty())
            return parameters;
        List<String> numberList = new ArrayList<String>();
        List<String> dateList = new ArrayList<String>();
        List<String> evaluationList = new ArrayList<String>();

        for (WeldPatternUI wp : weldPatterns){
            RadiationTestUI rt = wp.getRadiationTest();
            if (rt==null)
                continue;
            EvaluationUI rtEvaluation = rt.getEvaluation();
            if (!numberList.contains(rt.getNumber())){
                numberList.add(rt.getNumber());
            }
            if (!dateList.contains(rt.getDateFormat())){
                dateList.add(rt.getDateFormat()+DATE_SUFFIX);
            }
            if (rtEvaluation==null)
                continue;
            if (!evaluationList.contains(rtEvaluation.getType())){
                evaluationList.add(rtEvaluation.getType());
            }
        }

        StringBuilder numbers = new StringBuilder();
        StringBuilder dates = new StringBuilder();
        StringBuilder evaluations = new StringBuilder();

        for (String num : numberList){
            numbers.append(num);
            numbers.append(SEMICOLON_SEPARATOR);
        }
        for (String date: dateList){
            dates.append(date);
            dates.append(SEMICOLON_SEPARATOR);
        }
        for (String evaluation : evaluationList){
            evaluations.append(evaluation);
            evaluations.append(SEMICOLON_SEPARATOR);
        }
        deleteLastSeparator(numbers, SEMICOLON_SEPARATOR);
        deleteLastSeparator(dates, SEMICOLON_SEPARATOR);
        deleteLastSeparator(evaluations, SEMICOLON_SEPARATOR);

        parameters.replace(KEY_WELD_PATTERN_RT_NUMBERS, numbers.toString());
        parameters.replace(KEY_WELD_PATTERN_RT_DATE, dates.toString());
        parameters.replace(KEY_WELD_PATTERN_RT_EVALUATION, evaluations.toString());
        return parameters;
    }

    private Map<String, String> getWeldPatternMTParameters(List<WeldPatternUI> weldPatterns){
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(KEY_WELD_PATTERN_MT_NUMBERS, "");
        parameters.put(KEY_WELD_PATTERN_MT_DATE, "");
        parameters.put(KEY_WELD_PATTERN_MT_EVALUATION, "");
        if (weldPatterns == null || weldPatterns.isEmpty())
            return parameters;
        List<String> numberList = new ArrayList<String>();
        List<String> dateList = new ArrayList<String>();
        List<String> evaluationList = new ArrayList<String>();

        for (WeldPatternUI wp : weldPatterns){
            MechanicalTestUI mt = wp.getMechanicalTest();
            if (mt==null)
                continue;
            EvaluationUI mtEvaluation = mt.getEvaluation();
            if (!numberList.contains(mt.getNumber())){
                numberList.add(mt.getNumber());
            }
            if (!dateList.contains(mt.getDateFormat())){
                dateList.add(mt.getDateFormat()+DATE_SUFFIX);
            }
            if (mtEvaluation==null)
                continue;
            if (!evaluationList.contains(mtEvaluation.getType())){
                evaluationList.add(mtEvaluation.getType());
            }
        }

        StringBuilder numbers = new StringBuilder();
        StringBuilder dates = new StringBuilder();
        StringBuilder evaluations = new StringBuilder();

        for (String num : numberList){
            numbers.append(num);
            numbers.append(SEMICOLON_SEPARATOR);
        }
        for (String date: dateList){
            dates.append(date);
            dates.append(SEMICOLON_SEPARATOR);
        }
        for (String evaluation : evaluationList){
            evaluations.append(evaluation);
            evaluations.append(SEMICOLON_SEPARATOR);
        }

        deleteLastSeparator(numbers, SEMICOLON_SEPARATOR);
        deleteLastSeparator(dates, SEMICOLON_SEPARATOR);
        deleteLastSeparator(evaluations, SEMICOLON_SEPARATOR);

        parameters.replace(KEY_WELD_PATTERN_MT_NUMBERS, numbers.toString());
        parameters.replace(KEY_WELD_PATTERN_MT_DATE, dates.toString());
        parameters.replace(KEY_WELD_PATTERN_MT_EVALUATION, evaluations.toString());
        return parameters;
    }

    private String getNextDateCertification(Date protDate){
        if (protDate==null){
            return NULL_FIELD;
        }
        LocalDate nextDate = DateUtil.getLocalDate(protDate).plusYears(PERIOD_CERTIFICATION);
        return DateUtil.format(nextDate)+DATE_SUFFIX;
    }

    private List<WeldPatternReportEntity> getPatternsReportEntities(List<WeldPatternUI> weldPatterns){
        List<WeldPatternReportEntity> resultList = new ArrayList<WeldPatternReportEntity>();
        if (weldPatterns==null)
            return resultList;
        for (WeldPatternUI wp : weldPatterns){
            resultList.add(new WeldPatternReportEntity(wp));
        }
        return resultList;
    }



    public void deleteWeldPatternsWithoutMechTest(){
        if (this.patterns==null || patterns.isEmpty() )
            return;
        List<WeldPatternReportEntity> result = new ArrayList<WeldPatternReportEntity>();

        for (WeldPatternReportEntity weldPattern : this.patterns){
            if (!weldPattern.getMtEvaluation().equals(NULL_FIELD)){
                result.add(weldPattern);
            }
        }
        this.patterns.clear();
        this.patterns.addAll(result);
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public String getFio() {
        return fio;
    }

    public String getMark() {
        return mark;
    }

    public String getNdtDocs() {
        return ndtDocs;
    }

    public List<WeldPatternReportEntity> getPatterns() {
        return patterns;
    }
}
