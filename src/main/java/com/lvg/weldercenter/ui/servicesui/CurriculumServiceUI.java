package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.exceptions.WelderException;
import com.lvg.weldercenter.models.Curriculum;
import com.lvg.weldercenter.ui.entities.CurriculumUI;

/**
 * Created by Victor on 06.08.2015.
 */
public interface CurriculumServiceUI {
    public Curriculum getCurriculumFromUIModel(CurriculumUI curriculumUI);
    public Long saveCurriculumUI(CurriculumUI curriculumUI) throws WelderException;
    public void deleteCurriculumUI(CurriculumUI curriculumUI) throws WelderException;

}
