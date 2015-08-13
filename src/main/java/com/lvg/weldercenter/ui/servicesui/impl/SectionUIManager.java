package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.exceptions.WelderException;
import com.lvg.weldercenter.models.Section;
import com.lvg.weldercenter.services.CurriculumService;
import com.lvg.weldercenter.services.SectionService;
import com.lvg.weldercenter.ui.entities.CurriculumUI;
import com.lvg.weldercenter.ui.entities.SectionUI;
import com.lvg.weldercenter.ui.entities.TopicUI;
import com.lvg.weldercenter.ui.servicesui.CurriculumServiceUI;
import com.lvg.weldercenter.ui.servicesui.SectionServiceUI;
import com.lvg.weldercenter.ui.servicesui.TopicServiceUI;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 06.08.2015.
 */
public class SectionUIManager implements SectionServiceUI {
    private final Logger LOGGER = Logger.getLogger(SectionUIManager.class);

    @Autowired
    SectionService sectionService;
    @Autowired
    CurriculumService curriculumService;

    @Autowired
    CurriculumServiceUI curriculumServiceUI;
    @Autowired
    TopicServiceUI topicServiceUI;


    @Override
    public Section getSectionFromUIModel(SectionUI sectionUI) {
        if (sectionUI == null)
            return null;
        Section section = sectionService.get(sectionUI.getId());
        if (section != null){
            updateSectionFromUIModel(section, sectionUI);
        }else {
            section = new Section();
            updateSectionFromUIModel(section, sectionUI);
        }
        return section;
    }

    @Override
    public List<Section> getSectionListFromObsList(ObservableList<SectionUI> obsList) {
        if (obsList == null)
            return null;
        List<Section> sections = new ArrayList<Section>();
        for (SectionUI sectionUI : obsList){
            Section section = getSectionFromUIModel(sectionUI);
            if (section != null){
                sections.add(section);
            }
        }
        return sections;
    }

    @Override
    public Long saveSectionUI(SectionUI sectionUI, CurriculumUI parentCurriculum) throws WelderException {
        if (sectionUI == null)
            throw new WelderException("SAVE SECTION_UI: section is null");
        SectionUI updSection = parentCurriculum.getSections().get(parentCurriculum.getSections().indexOf(sectionUI));
        if (updSection == null)
            throw new WelderException("SAVE SECTION_UI: section not find in parent curriculum");

        Section section = getSectionFromUIModel(updSection);
        Long id;
        if (section.getSectionId() != null && section.getSectionId() !=0){
            sectionService.update(section);
            id = section.getSectionId();
            LOGGER.debug("SAVE SECTION_UI: section has been updated in DB");
        }else {
            id = sectionService.insert(section);
            updSection.setId(id);
            LOGGER.debug("SAVE SECTION_UI: section has been inserted in DB");
        }
        curriculumServiceUI.saveCurriculumUI(parentCurriculum);
        return id;
    }

    @Override
    public void deleteSectionUI(SectionUI sectionUI) throws WelderException {
        if (sectionUI == null)
            throw new WelderException("DELETE SECTION_UI: section is null");
        Section section = getSectionFromUIModel(sectionUI);
        if (section.getSectionId() != null && section.getSectionId() != 0){
            for (TopicUI topicUI : sectionUI.getTopics())
                topicServiceUI.deleteTopicUI(topicUI);
            sectionService.delete(section);
            LOGGER.debug("DELETE SECTION_UI: section has been deleted from DB");
        }
    }

    private void updateSectionFromUIModel(Section updSection, SectionUI uiModel){
        updSection.setTitle(uiModel.getTitle());
        updSection.setDescription(uiModel.getDescription());
        updSection.setOrderIndex(uiModel.getOrderIndex());
        updSection.setTopics(topicServiceUI.getTopicsListFromObsList(uiModel.getTopics()));
    }
}
