package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.WeldJoinType;
import com.lvg.weldercenter.ui.entities.WeldJoinTypeUI;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Victor on 22.06.2015.
 */
public interface WeldJoinTypeServiceUI {

    public WeldJoinType getWeldJoinTypeFromUIModel(WeldJoinTypeUI weldJoinTypeUI);
    public List<WeldJoinType> getWeldJoinTypeListFromObs(ObservableList<WeldJoinTypeUI> obsList);
}
