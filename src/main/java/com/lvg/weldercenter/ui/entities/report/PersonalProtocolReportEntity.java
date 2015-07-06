package com.lvg.weldercenter.ui.entities.report;

import com.lvg.weldercenter.models.*;
import com.lvg.weldercenter.ui.entities.*;
import com.lvg.weldercenter.ui.util.DateUtil;

import java.time.LocalDate;
import java.util.*;

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



    private final String WELD_PATTERN_MARK_SEPARATOR = " /";
    private final String NULL_STRING = "NULL";
    private final String YES_STRING = "есть";
    private final String NO_STRING = "нет";


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
        parameters.replace(KEY_PROT_DATE, personalProtocolUI.getDatePeriodicalCertFormat());
        if (totalProtocolUI.getCommissionCertification()!=null) {
            parameters.replace(KEY_COMISSION_HEAD, totalProtocolUI.getCommissionCertification().getHead().getFormatTeacherFullName("SUR-nn-sec"));
            parameters.replace(KEY_COMISSION_NDT_SPEC, totalProtocolUI.getCommissionCertification().getNdtSpecialist().getFormatTeacherFullName("SUR-nn-sec"));
            parameters.replace(KEY_COMISSION_SAFETY_SPEC, totalProtocolUI.getCommissionCertification().getSafetySpecialist().getFormatTeacherFullName("SUR-nn-sec"));
            parameters.replace(KEY_COMISSION_WELD_SPEC, totalProtocolUI.getCommissionCertification().getWeldSpecialist().getFormatTeacherFullName("SUR-nn-sec"));
        }
        parameters.replace(KEY_NDT_DOCUMENTS,getNdtDocs(personalProtocolUI.getNdtDocuments()));
        if (personalProtocolUI.getWelder()!=null) {
            parameters.replace(KEY_WELDER_FULL_NAME, personalProtocolUI.getWelder().getFormatName("SUR-NN-SEC"));
            parameters.replace(KEY_WELDER_BIRTHDAY, personalProtocolUI.getWelder().getBirthdayFormat());
            parameters.replace(KEY_WELDER_DOC_NUMBER, personalProtocolUI.getWelder().getDocNumber());
            parameters.replace(KEY_WELDER_EXPERIENCE, getExperience(personalProtocolUI.getWelder().getDateBegin()));
            parameters.replace(KEY_WELDER_ATTEST_TYPE, personalProtocolUI.getAttestType());
        }
        if (personalProtocolUI.getWeldPatterns()!=null && !personalProtocolUI.getWeldPatterns().isEmpty()) {
            List<WeldPatternUI> weldPatterns = personalProtocolUI.getWeldPatterns();
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
            return NULL_STRING;
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
            return NULL_STRING;
        LocalDate begin = DateUtil.getLocalDate(dateBegin);
        return LocalDate.now().getYear()-begin.getYear()+"";
    }

    private String getWeldPatternsMarks(List<WeldPatternUI> weldPatterns){
        if (weldPatterns==null || weldPatterns.isEmpty())
            return NULL_STRING;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp: weldPatterns){
            result.append(wp.getMark());
            if (weldPatterns.iterator().hasNext())
                result.append(WELD_PATTERN_MARK_SEPARATOR);
        }
        return result.toString();
    }

    private String getWeldPatternWeldMethods(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_STRING;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp : weldPatterns){
            if (wp.getWeldMethod()!=null)
                result.append(wp.getWeldMethod().getCode());
            else
                result.append(NULL_STRING);

            if (weldPatterns.iterator().hasNext())
                result.append("; ");
        }
        return result.toString();
    }

    private String getWeldPatternDetailTypes(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_STRING;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp : weldPatterns){
            if (wp.getWeldDetail()!=null)
                result.append(wp.getWeldDetail().getCode());
            else
                result.append(NULL_STRING);

            if (weldPatterns.iterator().hasNext())
                result.append("; ");
        }
        return result.toString();
    }

    private String getWeldPatternWeldPositions(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_STRING;
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
            return NULL_STRING;
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
            return NULL_STRING;
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
            return NULL_STRING;
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
            return NULL_STRING;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp : weldPatterns){
            SteelTypeUI steelTypeUI = wp.getSteelType();
            if (steelTypeUI==null)
                continue;
            SteelGroupUI steelGroupUI = steelTypeUI.getSteelGroupUI();
            if (steelGroupUI==null)
                continue;

            if (!result.toString().contains(steelGroupUI.getGroup())){
                result.append(steelGroupUI.getGroup()+" ");
                if (!result.toString().contains(steelTypeUI.getType())) {
                    result.append(steelTypeUI.getType());
                    if (weldPatterns.iterator().hasNext())
                        result.append("; ");
                }
            }
        }
        return result.toString();
    }

    private String getWeldPatternThicknesses(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_STRING;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp : weldPatterns){
            if (wp.getThickness()!= 0){
                if (!result.toString().contains(wp.getThickness()+"")){
                    result.append(wp.getThickness());
                    if (weldPatterns.iterator().hasNext())
                        result.append("; ");
                }
            }
        }
        return result.toString();
    }

    private String getWeldPatternDiameters(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_STRING;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp : weldPatterns){
            if (wp.getDiameter()!= 0){
                if (!result.toString().contains(wp.getDiameter()+"")){
                    result.append(wp.getDiameter());
                    if (weldPatterns.iterator().hasNext())
                        result.append("; ");
                }
            }
        }
        return result.toString();
    }

    private String getWeldPatternElectrodeWire(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_STRING;
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
            if (electrodes.iterator().hasNext() || !weldWires.isEmpty())
                result.append("; ");
        }
        for (String ww: weldWires){
            result.append(ww);
            if (weldWires.iterator().hasNext())
                result.append("; ");
        }
        return result.toString();

    }

    private String getWeldPatternGas(List<WeldPatternUI> weldPatterns){
        if (weldPatterns == null || weldPatterns.isEmpty())
            return NULL_STRING;
        StringBuilder result = new StringBuilder();
        for (WeldPatternUI wp : weldPatterns){
            WeldGasUI weldGas = wp.getWeldGas();
            if (weldGas!=null){
                if (!result.toString().contains(weldGas.getType())){
                    result.append(weldGas.getType());
                    if (weldPatterns.iterator().hasNext())
                        result.append("; ");
                }

            }
        }
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
                dateList.add(vt.getDateFormat());
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
            if (numberList.iterator().hasNext())
                numbers.append("; ");
        }
        for (String date: dateList){
            dates.append(date);
            if (dateList.iterator().hasNext())
                dates.append("; ");
        }
        for (String evaluation : evaluationList){
            evaluations.append(evaluation);
            if (evaluationList.iterator().hasNext())
                evaluations.append("; ");
        }

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
                dateList.add(rt.getDateFormat());
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
            if (numberList.iterator().hasNext())
                numbers.append("; ");
        }
        for (String date: dateList){
            dates.append(date);
            if (dateList.iterator().hasNext())
                dates.append("; ");
        }
        for (String evaluation : evaluationList){
            evaluations.append(evaluation);
            if (evaluationList.iterator().hasNext())
                evaluations.append("; ");
        }

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
                dateList.add(mt.getDateFormat());
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
            if (numberList.iterator().hasNext())
                numbers.append("; ");
        }
        for (String date: dateList){
            dates.append(date);
            if (dateList.iterator().hasNext())
                dates.append("; ");
        }
        for (String evaluation : evaluationList){
            evaluations.append(evaluation);
            if (evaluationList.iterator().hasNext())
                evaluations.append("; ");
        }

        parameters.replace(KEY_WELD_PATTERN_MT_NUMBERS, numbers.toString());
        parameters.replace(KEY_WELD_PATTERN_MT_DATE, dates.toString());
        parameters.replace(KEY_WELD_PATTERN_MT_EVALUATION, evaluations.toString());
        return parameters;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
