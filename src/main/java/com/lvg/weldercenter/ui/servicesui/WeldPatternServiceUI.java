package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.WeldPattern;
import com.lvg.weldercenter.ui.entities.WeldPatternUI;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Victor on 06.04.2015.
 */
public interface WeldPatternServiceUI {
    public WeldPattern getWeldPatternFromWeldPatternUI(WeldPatternUI weldPatternUI);
    public List<WeldPattern> getWeldPatternListFromObsList(ObservableList<WeldPatternUI> obsList);
}
