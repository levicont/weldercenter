package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.VisualTest;
import com.lvg.weldercenter.services.VisualTestService;
import com.lvg.weldercenter.ui.entities.VisualTestUI;
import com.lvg.weldercenter.ui.servicesui.EvaluationServiceUI;
import com.lvg.weldercenter.ui.servicesui.VisualTestServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 03.04.2015.
 */
public class VisualTestUIManager implements VisualTestServiceUI {

    @Autowired
    EvaluationServiceUI evaluationServiceUI;
    @Autowired
    VisualTestService visualTestService;

    @Override
    public VisualTest getVisualTestFromVisualTestUI(VisualTestUI visualTestUI) {
        if (visualTestUI!=null)
        return null;
        VisualTest vt = visualTestService.get(visualTestUI.getId());
        if (vt != null){
            updateVisualTestFromVisualTestUI(vt,visualTestUI);
            return vt;
        }else {
            vt = new VisualTest();
            updateVisualTestFromVisualTestUI(vt,visualTestUI);
            return vt;
        }
    }

    public void updateVisualTestFromVisualTestUI(VisualTest updateVT, VisualTestUI modelVT){
        updateVT.setNumber(modelVT.getNumber());
        updateVT.setProtDate(modelVT.getDate());
        updateVT.setDefects(modelVT.getDefects());
        updateVT.setEvaluation(evaluationServiceUI.getEvaluationFromEvaluationUI(modelVT.getEvaluation()));
    }
}
