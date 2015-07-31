package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.PatternDiameter;
import com.lvg.weldercenter.services.PatternDiameterService;
import com.lvg.weldercenter.ui.entities.PatternDiameterUI;
import com.lvg.weldercenter.ui.servicesui.PatternDiameterServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 31.07.2015.
 */
public class PatternDiameterUIManager implements PatternDiameterServiceUI {
    @Autowired
    PatternDiameterService patternDiameterService;

    @Override
    public PatternDiameter getPatternDiameterFromUIModel(PatternDiameterUI patternDiameterUI) {
        if (patternDiameterUI == null)
            return null;
        PatternDiameter patternDiameter = patternDiameterService.get(patternDiameterUI.getId());

        if (patternDiameter != null){
            updatePatternDiameterFromUIModel(patternDiameter, patternDiameterUI);
        }else {
            patternDiameter = new PatternDiameter();
            updatePatternDiameterFromUIModel(patternDiameter, patternDiameterUI);
        }
        return patternDiameter;
    }

    private void updatePatternDiameterFromUIModel(PatternDiameter updPatternDiameter, PatternDiameterUI uiModel){
        updPatternDiameter.setDiameter(uiModel.getDiameter());
    }
}
