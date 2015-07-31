package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.PatternThickness;
import com.lvg.weldercenter.ui.entities.PatternThicknessUI;

/**
 * Created by Victor on 31.07.2015.
 */
public interface PatternThicknessServiceUI {
    public PatternThickness getPatternThicknessFromUIModel(PatternThicknessUI patternThicknessUI);
}
