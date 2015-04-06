package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.Evaluation;
import com.lvg.weldercenter.services.EvaluationService;
import com.lvg.weldercenter.ui.entities.EvaluationUI;
import com.lvg.weldercenter.ui.servicesui.EvaluationServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 03.04.2015.
 */
public class EvaluationUIManager implements EvaluationServiceUI {

    @Autowired
    private EvaluationService evaluationService;

    @Override
    public Evaluation getEvaluationFromEvaluationUI(EvaluationUI evaluationUI) {
        if (evaluationUI==null)
            return null;
        Evaluation evaluation = evaluationService.get(evaluationUI.getId());

        if(evaluation!=null){
            evaluation.setType(evaluationUI.getType());
            return evaluation;
        }else {
            evaluation = new Evaluation();
            evaluation.setType(evaluationUI.getType());
            return evaluation;
        }

    }
}
