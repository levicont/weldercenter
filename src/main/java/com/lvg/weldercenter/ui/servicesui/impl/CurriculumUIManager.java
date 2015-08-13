package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.exceptions.WelderException;
import com.lvg.weldercenter.models.Curriculum;
import com.lvg.weldercenter.services.CurriculumService;
import com.lvg.weldercenter.ui.entities.CurriculumUI;
import com.lvg.weldercenter.ui.entities.SectionUI;
import com.lvg.weldercenter.ui.servicesui.CurriculumServiceUI;
import com.lvg.weldercenter.ui.servicesui.SectionServiceUI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 06.08.2015.
 */
public class CurriculumUIManager implements CurriculumServiceUI {
    private final Logger LOGGER = Logger.getLogger(CurriculumUIManager.class);

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

    @Override
    public Long saveCurriculumUI(CurriculumUI curriculumUI)throws WelderException{
        if (curriculumUI == null)
            throw new WelderException("SAVE CURRICULUM: curriculum is null");
        Curriculum curriculum = getCurriculumFromUIModel(curriculumUI);
        Long id = null;
        if (curriculum.getCurriculumId() != null && curriculum.getCurriculumId() != 0){
            curriculumService.update(curriculum);
            id =  curriculum.getCurriculumId();
            LOGGER.debug("SAVE CURRICULUM: curriculum has been updated");
        }else {
            id = curriculumService.insert(curriculum);
            LOGGER.debug("SAVE CURRICULUM: curriculum has been inserted");
        }
        return id;
    }

    @Override
    public void deleteCurriculumUI(CurriculumUI curriculumUI) throws WelderException {
        if (curriculumUI == null)
            throw new WelderException("DELETE CURRICULUM_UI: curriculum is null");
        Curriculum curriculum = getCurriculumFromUIModel(curriculumUI);
        if (curriculum.getCurriculumId() != null && curriculum.getCurriculumId() != 0){
            for (SectionUI sectionUI : curriculumUI.getSections())
                sectionServiceUI.deleteSectionUI(sectionUI);
            curriculumService.delete(curriculum);
            LOGGER.debug("DELETE CURRICULUM_UI: curriculum has been deleted from DB");
        }
    }

    private void updateCurriculumFromUIModel(Curriculum updCurr, CurriculumUI uiModel){
        updCurr.setTitle(uiModel.getTitle());
        updCurr.setDescription(uiModel.getDescription());
        updCurr.setSections(sectionServiceUI.getSectionListFromObsList(uiModel.getSections()));
    }
}
