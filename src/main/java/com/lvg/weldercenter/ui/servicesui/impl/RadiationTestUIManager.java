package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.RadiationTest;
import com.lvg.weldercenter.services.RadiationTestService;
import com.lvg.weldercenter.ui.entities.RadiationTestUI;
import com.lvg.weldercenter.ui.servicesui.EvaluationServiceUI;
import com.lvg.weldercenter.ui.servicesui.RadiationTestServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 03.04.2015.
 */
public class RadiationTestUIManager implements RadiationTestServiceUI {

    @Autowired
    private RadiationTestService radiationTestService;
    @Autowired
    private EvaluationServiceUI evaluationServiceUI;

    @Override
    public RadiationTest getRadiationTestFromUI(RadiationTestUI radiationTestUI) {
        if (radiationTestUI==null)
            return null;
        RadiationTest radiationTest = radiationTestService.get(radiationTestUI.getId());

        if(radiationTest!=null){
           updateRadiationTestFromRadiationTestUI(radiationTest,radiationTestUI);
            return radiationTest;
        }else {
            radiationTest = new RadiationTest();
            updateRadiationTestFromRadiationTestUI(radiationTest,radiationTestUI);
            return radiationTest;
        }

    }

    private void updateRadiationTestFromRadiationTestUI(RadiationTest updatedRT, RadiationTestUI modelRT){
        updatedRT.setNumber(modelRT.getNumber());
        updatedRT.setProtDate(modelRT.getDate());
        updatedRT.setSensitivity(modelRT.getSensitivity());
        updatedRT.setDefects(modelRT.getDefects());
        updatedRT.setEvaluation(evaluationServiceUI.getEvaluationFromEvaluationUI(modelRT.getEvaluation()));
    }
}
