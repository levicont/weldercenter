package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.Qualification;
import com.lvg.weldercenter.services.QualificationService;
import com.lvg.weldercenter.ui.entities.QualificationUI;
import com.lvg.weldercenter.ui.servicesui.QualificationServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor Levchenko LVG Corp. on 04.08.15.
 */
public class QualificationUIManager implements QualificationServiceUI {
    @Autowired
    QualificationService qualificationService;

    @Override
    public Qualification getQualificationFromUIModel(QualificationUI qualificationUI) {
        if (qualificationUI == null)
            return null;
        Qualification result = qualificationService.get(qualificationUI.getId());
        if (result != null){
            updateQualificationFromUIModel(result, qualificationUI);
        }else {
            result = new Qualification();
            updateQualificationFromUIModel(result, qualificationUI);
        }
        return result;
    }

    private void updateQualificationFromUIModel(Qualification updQulification, QualificationUI uiModel){
        updQulification.setType(uiModel.getType());
    }
}
