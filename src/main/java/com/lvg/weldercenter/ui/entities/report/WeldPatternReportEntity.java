package com.lvg.weldercenter.ui.entities.report;

import com.lvg.weldercenter.models.WeldPattern;
import com.lvg.weldercenter.models.WeldPosition;
import com.lvg.weldercenter.ui.entities.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Victor on 09.07.2015.
 */
public class WeldPatternReportEntity extends GenericReportEntity{
    private final String ZERO = "0";

    private final String KEY_VT_DEFECTS = "VT_DEFECTS";
    private final String KEY_VT_EVALUATION = "VT_EVALUATION";
    private final String KEY_RT_DEFECTS = "RT_DEFECTS";
    private final String KEY_RT_EVALUATION = "RT_EVALUATION";
    private final String KEY_RT_SENSITIVITY = "RT_SENSITIVITY";
    private final String KEY_MT_ANGLE = "MT_ANGLE";
    private final String KEY_MT_EVALUATION = "MT_EVALUATION";



    private String mark;
    private String weldMethod;
    private String thickness;
    private String diameter;
    private String size;
    private String weldType;
    private String weldPosition;
    private String weldMaterials;
    private String electrodes;
    private String vtDefects;
    private String vtEvaluation;
    private String rtDefects;
    private String rtEvaluation;
    private String rtSensitivity;
    private String mtAngle;
    private String mtEvaluation;

    public WeldPatternReportEntity(WeldPatternUI weldPatternUI){
        this.mark = weldPatternUI.getMark();
        if (weldPatternUI.getWeldMethod()!=null)
            this.weldMethod = weldPatternUI.getWeldMethod().getCode();
        else
            this.weldMethod = NULL_FIELD;
        this.thickness = String.valueOf(weldPatternUI.getThickness());
        this.diameter = String.valueOf(weldPatternUI.getDiameter());
        this.size = getSizeFromThicknessDiameter(this.thickness,this.diameter);
        this.weldType = getWeldPatternWeldJoinTypes(weldPatternUI.getWeldJoinTypes());
        this.weldPosition = getWeldPatternWeldPositions(weldPatternUI.getWeldPositions());
        this.weldMaterials = getWeldPatternWeldMaterial(weldPatternUI.getSteelType());
        this.electrodes = getElectrodesWeldWires(weldPatternUI);
        Map<String, String> parameters = getWeldPatternVTParameters(weldPatternUI.getVisualTest());
        this.vtDefects = parameters.get(KEY_VT_DEFECTS);
        this.vtEvaluation = parameters.get(KEY_VT_EVALUATION);
        parameters = getWeldPatternRTParameters(weldPatternUI.getRadiationTest());
        this.rtDefects = parameters.get(KEY_RT_DEFECTS);
        this.rtEvaluation = parameters.get(KEY_RT_EVALUATION);
        this.rtSensitivity = parameters.get(KEY_RT_SENSITIVITY);
        parameters = getWeldPatternMTParameters(weldPatternUI.getMechanicalTest());
        this.mtAngle = parameters.get(KEY_MT_ANGLE);
        this.mtEvaluation = parameters.get(KEY_MT_EVALUATION);
    }

    private String getSizeFromThicknessDiameter(String thickness, String diameter){
        Double thicknessD;
        Double diameterD;
        try {
            thicknessD = Double.parseDouble(thickness);
            diameterD = Double.parseDouble(diameter);
        }catch (NumberFormatException ex){
            thicknessD = 0.0;
            diameterD = 0.0;
        }
        if(diameterD == 0)
            return thickness;
        return diameterD+SIZE_SEPARATOR+thicknessD;
    }
    private String getWeldPatternWeldJoinTypes(List<WeldJoinTypeUI> weldJoinTypes){
        if (weldJoinTypes==null)
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        for (WeldJoinTypeUI wjt: weldJoinTypes){
            result.append(wjt.getType());
            result.append(SPACE_SEPARATOR);
        }
        deleteLastSeparator(result,SPACE_SEPARATOR);
        return result.toString();
    }
    private String getWeldPatternWeldPositions(List<WeldPositionUI> weldPositions){
        if (weldPositions==null)
            return NULL_FIELD;
        StringBuilder result = new StringBuilder();
        for (WeldPositionUI wp: weldPositions){
            result.append(wp.getCode());
            result.append(SEMICOLON_SEPARATOR);
        }
        deleteLastSeparator(result, SEMICOLON_SEPARATOR);
        return result.toString();
    }
    private String getWeldPatternWeldMaterial(SteelTypeUI steelTypeUI){
        if (steelTypeUI==null)
            return NULL_FIELD;
        SteelGroupUI steelGroup = steelTypeUI.getSteelGroupUI();
        if (steelTypeUI.getSteelGroup()==null || steelGroup.getGroup().isEmpty())
            return steelTypeUI.getType();
        return steelGroup.getGroup()+COLON_SEPARATOR+steelTypeUI.getType();
    }
    private String getElectrodesWeldWires(WeldPatternUI weldPatternUI){
        if (weldPatternUI.getElectrode()!=null)
            return weldPatternUI.getElectrode().getType();
        if (weldPatternUI.getWeldWire()!=null)
            return weldPatternUI.getWeldWire().getType();
        return NULL_FIELD;
    }
    private Map<String,String> getWeldPatternVTParameters(VisualTestUI visualTest){
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put(KEY_VT_DEFECTS, NULL_FIELD);
        resultMap.put(KEY_VT_EVALUATION, NULL_FIELD);

        if (visualTest==null)
            return resultMap;
        resultMap.replace(KEY_VT_DEFECTS, visualTest.getDefects());
        if (visualTest.getEvaluation()==null)
            return resultMap;
        resultMap.replace(KEY_VT_EVALUATION,visualTest.getEvaluation().getType());
        return resultMap;
    }
    private Map<String,String> getWeldPatternRTParameters(RadiationTestUI radiationTest){
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put(KEY_RT_DEFECTS, NULL_FIELD);
        resultMap.put(KEY_RT_EVALUATION, NULL_FIELD);
        resultMap.put(KEY_RT_SENSITIVITY, NULL_FIELD);

        if (radiationTest==null)
            return resultMap;
        resultMap.replace(KEY_RT_DEFECTS, radiationTest.getDefects());
        resultMap.replace(KEY_RT_SENSITIVITY, radiationTest.getSensitivity());
        if (radiationTest.getEvaluation()==null)
            return resultMap;
        resultMap.replace(KEY_RT_EVALUATION,radiationTest.getEvaluation().getType());
        return resultMap;
    }
    private Map<String,String> getWeldPatternMTParameters(MechanicalTestUI mechanicalTest){
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put(KEY_MT_ANGLE, NULL_FIELD);
        resultMap.put(KEY_MT_EVALUATION, NULL_FIELD);

        if (mechanicalTest==null)
            return resultMap;
        resultMap.replace(KEY_MT_ANGLE, String.valueOf(mechanicalTest.getAngle()));
        if (mechanicalTest.getEvaluation()==null)
            return resultMap;
        resultMap.replace(KEY_MT_EVALUATION,mechanicalTest.getEvaluation().getType());
        return resultMap;
    }

    public String getMark() {
        return mark;
    }

    public String getWeldMethod() {
        return weldMethod;
    }

    public String getThickness() {
        return thickness;
    }

    public String getDiameter() {
        return diameter;
    }

    public String getSize() {
        return size;
    }

    public String getWeldType() {
        return weldType;
    }

    public String getWeldPosition() {
        return weldPosition;
    }

    public String getWeldMaterials() {
        return weldMaterials;
    }

    public String getVtDefects() {
        return vtDefects;
    }

    public String getVtEvaluation() {
        return vtEvaluation;
    }

    public String getRtDefects() {
        return rtDefects;
    }

    public String getRtEvaluation() {
        return rtEvaluation;
    }

    public String getRtSensitivity() {
        return rtSensitivity;
    }

    public String getMtAngle() {
        return mtAngle;
    }

    public String getMtEvaluation() {
        return mtEvaluation;
    }

    public String getElectrodes() {
        return electrodes;
    }
}