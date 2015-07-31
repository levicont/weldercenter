package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.PatternThickness;
import com.lvg.weldercenter.services.PatternThicknessService;
import com.lvg.weldercenter.ui.entities.PatternThicknessUI;
import com.lvg.weldercenter.ui.servicesui.PatternThicknessServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 31.07.2015.
 */
public class PatternThicknessUIManager implements PatternThicknessServiceUI {
    @Autowired
    PatternThicknessService patternThicknessService;

    @Override
    public PatternThickness getPatternThicknessFromUIModel(PatternThicknessUI patternThicknessUI) {
        if (patternThicknessUI == null)
            return null;
        PatternThickness patternThickness = patternThicknessService.get(patternThicknessUI.getId());

        if (patternThickness != null){
            updatePatternThicknessFromUIModel(patternThickness, patternThicknessUI);
        }else {
            patternThickness = new PatternThickness();
            updatePatternThicknessFromUIModel(patternThickness, patternThicknessUI);
        }
        return patternThickness;
    }

    private void updatePatternThicknessFromUIModel(PatternThickness updPatternThickness, PatternThicknessUI uiModel){
        updPatternThickness.setThickness(uiModel.getThickness());
    }
}
