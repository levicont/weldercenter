package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.Curriculum;
import com.lvg.weldercenter.services.CurriculumService;
import com.lvg.weldercenter.ui.entities.CurriculumUI;
import com.lvg.weldercenter.ui.servicesui.CurriculumServiceUI;
import com.lvg.weldercenter.ui.servicesui.SectionServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 06.08.2015.
 */
public class CurriculumUIManager implements CurriculumServiceUI {

    @Autowired
    CurriculumService curriculumService;

    @Autowired
    SectionServiceUI sectionServiceUI;

    @Override
    public Curriculum getCurriculumFromUIModel(CurriculumUI curriculumUI) {
        if (curriculumUI == null)
            return null;
        Curriculum curriculum = curriculumService.get(curriculumUI.getId());
        if (curriculum != null){
            updateCurriculumFromUIModel(curriculum, curriculumUI);
        }else {
            curriculum = new Curriculum();
            updateCurriculumFromUIModel(curriculum, curriculumUI);
        }
        return curriculum;
    }

    private void updateCurriculumFromUIModel(Curriculum updCurr, CurriculumUI uiModel){
        updCurr.setTitle(uiModel.getTitle());
        updCurr.setDescription(uiModel.getDescription());
        updCurr.setSections(sectionServiceUI.getSectionListFromObsList(uiModel.getSections()));
    }
}
