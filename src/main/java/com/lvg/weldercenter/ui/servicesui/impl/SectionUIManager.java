package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.Section;
import com.lvg.weldercenter.services.SectionService;
import com.lvg.weldercenter.ui.entities.SectionUI;
import com.lvg.weldercenter.ui.servicesui.SectionServiceUI;
import com.lvg.weldercenter.ui.servicesui.TopicServiceUI;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 06.08.2015.
 */
public class SectionUIManager implements SectionServiceUI {

    @Autowired
    SectionService sectionService;
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

    private void updateSectionFromUIModel(Section updSection, SectionUI uiModel){
        updSection.setTitle(uiModel.getTitle());
        updSection.setDescription(uiModel.getDescription());
        updSection.setOrderIndex(uiModel.getOrderIndex());
        updSection.setTopics(topicServiceUI.getTopicsListFromObsList(uiModel.getTopics()));
    }
}
