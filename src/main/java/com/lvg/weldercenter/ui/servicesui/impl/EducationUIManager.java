package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.Education;
import com.lvg.weldercenter.services.EducationService;
import com.lvg.weldercenter.ui.entities.EducationUI;
import com.lvg.weldercenter.ui.servicesui.EducationServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 04.08.2015.
 */
public class EducationUIManager implements EducationServiceUI {

    @Autowired
    EducationService educationService;

    @Override
    public Education getEducationFromUIModel(EducationUI educationUI) {
        if (educationUI == null) {
            return null;
        }
        Education edu = educationService.get(educationUI.getId());
        if (edu != null){
            updateEducationFromUIModel(edu, educationUI);
            return edu;
        }else {
            edu = new Education();
            updateEducationFromUIModel(edu, educationUI);
            return edu;
        }
    }

    private void updateEducationFromUIModel(Education updEducation, EducationUI modelUI){
        updEducation.setType(modelUI.getType());
    }
}
