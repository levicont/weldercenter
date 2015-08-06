package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.Section;
import com.lvg.weldercenter.ui.entities.SectionUI;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Victor on 06.08.2015.
 */
public interface SectionServiceUI {
    public Section getSectionFromUIModel(SectionUI sectionUI);
    public List<Section> getSectionListFromObsList(ObservableList<SectionUI> obsList);
}
