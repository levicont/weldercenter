package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.Education;
import com.lvg.weldercenter.ui.entities.EducationUI;

/**
 * Created by Victor on 04.08.2015.
 */
public interface EducationServiceUI {
    public Education getEducationFromUIModel(EducationUI educationUI);
}
