package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.WeldPattern;
import com.lvg.weldercenter.services.WeldPatternService;
import com.lvg.weldercenter.ui.entities.WeldPatternUI;
import com.lvg.weldercenter.ui.servicesui.*;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 06.04.2015.
 */
public class WeldPatternUIManager implements WeldPatternServiceUI {

    @Autowired
    private WeldPatternService weldPatternService;
    @Autowired
    private WeldDetailServiceUI weldDetailServiceUI;
    @Autowired
    private SteelTypeServiceUI steelTypeServiceUI;
    @Autowired
    private WeldPositionServiceUI weldPositionServiceUI;
    @Autowired
    private WeldMethodServiceUI weldMethodServiceUI;
    @Autowired
    private ElectrodeServiceUI electrodeServiceUI;
    @Autowired
    private WeldWireServiceUI weldWireServiceUI;
    @Autowired
    private WeldGasServiceUI weldGasServiceUI;
    @Autowired
    private PersonalProtocolServiceUI personalProtocolServiceUI;
    @Autowired
    private RadiationTestServiceUI radiationTestServiceUI;
    @Autowired
    private VisualTestServiceUI visualTestServiceUI;
    @Autowired
    private MechanicalTestServiceUI mechanicalTestServiceUI;


    @Override
    public WeldPattern getWeldPatternFromWeldPatternUI(WeldPatternUI weldPatternUI) {
        if (weldPatternUI == null) {
            return null;
        }
        WeldPattern wp = weldPatternService.get(weldPatternUI.getId());
        if (wp != null){
            updateWeldPatternFromUIModel(wp, weldPatternUI);
        }else {
            wp = new WeldPattern();
            updateWeldPatternFromUIModel(wp, weldPatternUI);
        }
        return wp;
    }

    @Override
    public List<WeldPattern> getWeldPatternListFromObsList(ObservableList<WeldPatternUI> obsList) {
        if (obsList == null) {
            return null;
        }
        List<WeldPattern> weldPatternList = new ArrayList<WeldPattern>();
        for (WeldPatternUI wpUI : obsList){
            WeldPattern wp = getWeldPatternFromWeldPatternUI(wpUI);
            if (wp != null){
                weldPatternList.add(wp);
            }
        }
        return weldPatternList;
    }

    private void updateWeldPatternFromUIModel(WeldPattern updWeldPattern, WeldPatternUI modelWeldPattern){

        updWeldPattern.setDiameter(modelWeldPattern.getDiameter());
        updWeldPattern.setThickness(modelWeldPattern.getThickness());
        updWeldPattern.setMark(modelWeldPattern.getMark());
        updWeldPattern.setIsHeating(modelWeldPattern.getIsHeating());
        updWeldPattern.setIsHeatTreatment(modelWeldPattern.getIsHeatTreatment());
        updWeldPattern.setWeldDetail(weldDetailServiceUI.getWeldDetailFromUIModel(modelWeldPattern.getWeldDetail()));
        updWeldPattern.setSteelType(steelTypeServiceUI.getSteelTypeFromUIModel(modelWeldPattern.getSteelType()));
        updWeldPattern.setWeldPositions(weldPositionServiceUI.getWeldPositionListFromObsList(modelWeldPattern.getWeldPositions()));
        updWeldPattern.setWeldMethod(weldMethodServiceUI.getWeldMethodFromUI(modelWeldPattern.getWeldMethod()));
        updWeldPattern.setElectrode(electrodeServiceUI.getElectrodeFromUIModel(modelWeldPattern.getElectrode()));
        updWeldPattern.setWeldWire(weldWireServiceUI.getWeldWireFromUIModel(modelWeldPattern.getWeldWire()));
        updWeldPattern.setWeldGas(weldGasServiceUI.getWeldGasFromUIModel(modelWeldPattern.getWeldGas()));
        //updWeldPattern.setPersonalProtocol(personalProtocolServiceUI.getPersonalProtocolFromUIModel(
        //        modelWeldPattern.getPersonalProtocol()));
        updWeldPattern.setRadiationTest(radiationTestServiceUI.getRadiationTestFromUI(
                modelWeldPattern.getRadiationTest()));
        updWeldPattern.setVisualTest(visualTestServiceUI.getVisualTestFromVisualTestUI(
                modelWeldPattern.getVisualTest()
        ));
        updWeldPattern.setMechanicalTest(mechanicalTestServiceUI.getMechTestFromMechTestUI(
                modelWeldPattern.getMechanicalTest()
        ));


    }


}
