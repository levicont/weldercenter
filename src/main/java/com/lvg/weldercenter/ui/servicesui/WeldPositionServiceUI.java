package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.WeldPosition;
import com.lvg.weldercenter.ui.entities.WeldPositionUI;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Victor on 06.04.2015.
 */
public interface WeldPositionServiceUI {

    public WeldPosition getWeldPositionFromUIModel(WeldPositionUI weldPositionUI);
    public List<WeldPosition> getWeldPositionListFromObsList(ObservableList<WeldPositionUI> obsList);
}
