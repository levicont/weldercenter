package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.Evaluation;
import com.lvg.weldercenter.ui.entities.EvaluationUI;

/**
 * Created by Victor on 03.04.2015.
 */
public interface EvaluationServiceUI {

    public Evaluation getEvaluationFromEvaluationUI(EvaluationUI evaluationUI);
}
