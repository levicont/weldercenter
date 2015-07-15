package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.MechanicalTest;
import com.lvg.weldercenter.services.MechanicalTestService;
import com.lvg.weldercenter.ui.entities.MechanicalTestUI;
import com.lvg.weldercenter.ui.servicesui.EvaluationServiceUI;
import com.lvg.weldercenter.ui.servicesui.MechanicalTestServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 03.04.2015.
 */
public class MechanicalTestUIManager implements MechanicalTestServiceUI {
    @Autowired
    private EvaluationServiceUI evaluationServiceUI;
    @Autowired
    private MechanicalTestService mechanicalTestService;

    @Override
    public MechanicalTest getMechTestFromMechTestUI(MechanicalTestUI mechanicalTestUI) {
        if (mechanicalTestUI==null)
            return null;
        MechanicalTest mechTest = mechanicalTestService.get(mechanicalTestUI.getId());
        if (mechTest != null){
            updateMechTestFromMechTestUI(mechTest, mechanicalTestUI);
            return mechTest;
        }else {
            mechTest = new MechanicalTest();
            updateMechTestFromMechTestUI(mechTest, mechanicalTestUI);
            return mechTest;
        }
    }

    private void updateMechTestFromMechTestUI(MechanicalTest updMechTest, MechanicalTestUI modelMechTestUI){
        updMechTest.setNumber(modelMechTestUI.getNumber());
        updMechTest.setProtDate(modelMechTestUI.getDate());
        updMechTest.setAngle(modelMechTestUI.getAngle());
        updMechTest.setClearance(modelMechTestUI.getClearance());
        updMechTest.setEvaluation(evaluationServiceUI.getEvaluationFromEvaluationUI(modelMechTestUI.getEvaluation()));
    }
}
