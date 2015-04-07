package com.lvg.weldercenter.ui.util;

import com.lvg.weldercenter.models.WeldPattern;
import com.lvg.weldercenter.ui.entities.WeldPatternUI;
import org.apache.log4j.Logger;

/**
 * Created by Victor on 07.04.2015.
 */
public class Printer {
    private static final Logger LOGGER = Logger.getLogger(Printer.class);

    public static void printWeldPatternUI(WeldPatternUI weldPatternUI){
        LOGGER.debug("PRINT WELD_PATTERN_UI: id_weld_pattern: "+weldPatternUI.getId()+"\n"+
                        "\tweld_detail: "+weldPatternUI.getWeldDetail()+"\n"+
                        "\tdiameter: "+weldPatternUI.getDiameter()+"\n"+
                        "\tthickness: "+weldPatternUI.getThickness()+"\n"+
                        "\tmark: "+weldPatternUI.getMark()+"\n"+
                        "\theating: "+weldPatternUI.getIsHeating()+"\n"+
                        "\theat_treatment: "+weldPatternUI.getIsHeatTreatment()+"\n"+
                        "\tsteel_type: "+weldPatternUI.getSteelType()+"\n"+
                        "\tweld_positions: "+weldPatternUI.getWeldPositions()+"\n"+
                        "\tweld_method: "+weldPatternUI.getWeldMethod()+"\n"+
                        "\telectrode: "+weldPatternUI.getElectrode()+"\n"+
                        "\tweld_wire: "+weldPatternUI.getWeldWire()+"\n"+
                        "\tweld_gas: "+weldPatternUI.getWeldGas()+"\n"+
                        "\trt: "+weldPatternUI.getRadiationTest()+"\n"+
                        "\tvt: "+weldPatternUI.getVisualTest()+"\n"+
                        "\tmech_test: "+weldPatternUI.getMechanicalTest()+"\n"+
                        "\tpersonal_protocol: "+weldPatternUI.getPersonalProtocol()+"\n"

        );

    }
    public static void printWeldPattern(WeldPattern weldPattern){
        LOGGER.debug("PRINT WELD_PATTERN: id_weld_pattern: "+weldPattern.getWeldPatternId()+"\n"+
                        "\tweld_detail: "+weldPattern.getWeldDetail()+"\n"+
                        "\tdiameter: "+weldPattern.getDiameter()+"\n"+
                        "\tthickness: "+weldPattern.getThickness()+"\n"+
                        "\tmark: "+weldPattern.getMark()+"\n"+
                        "\theating: "+weldPattern.getIsHeating()+"\n"+
                        "\theat_treatment: "+weldPattern.getIsHeatTreatment()+"\n"+
                        "\tsteel_type: "+weldPattern.getSteelType()+"\n"+
                        "\tweld_positions: "+weldPattern.getWeldPositions()+"\n"+
                        "\tweld_method: "+weldPattern.getWeldMethod()+"\n"+
                        "\telectrode: "+weldPattern.getElectrode()+"\n"+
                        "\tweld_wire: "+weldPattern.getWeldWire()+"\n"+
                        "\tweld_gas: "+weldPattern.getWeldGas()+"\n"+
                        "\trt: "+weldPattern.getRadiationTest()+"\n"+
                        "\tvt: "+weldPattern.getVisualTest()+"\n"+
                        "\tmech_test: "+weldPattern.getMechanicalTest()+"\n"+
                        "\tpersonal_protocol: "+weldPattern.getPersonalProtocol()+"\n"

        );

    }
}
