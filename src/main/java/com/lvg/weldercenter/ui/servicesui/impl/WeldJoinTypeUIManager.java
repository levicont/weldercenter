package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.WeldJoinType;
import com.lvg.weldercenter.services.WeldJoinTypeService;
import com.lvg.weldercenter.ui.entities.WeldJoinTypeUI;
import com.lvg.weldercenter.ui.servicesui.WeldJoinTypeServiceUI;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 22.06.2015.
 */
public class WeldJoinTypeUIManager implements WeldJoinTypeServiceUI {

    @Autowired
    private WeldJoinTypeService weldJoinTypeService;


    @Override
    public WeldJoinType getWeldJoinTypeFromUIModel(WeldJoinTypeUI weldJoinTypeUI) {
        if (weldJoinTypeUI == null)
            return null;
        WeldJoinType wjt = weldJoinTypeService.get(weldJoinTypeUI.getId());
        if (wjt != null){
            updateWeldJoinTypeFromUIModel(wjt, weldJoinTypeUI);
            return wjt;
        }else {
            wjt = new WeldJoinType();
            updateWeldJoinTypeFromUIModel(wjt, weldJoinTypeUI);
            return wjt;
        }
    }

    @Override
    public List<WeldJoinType> getWeldJoinTypeListFromObs(ObservableList<WeldJoinTypeUI> obsList) {
        if (obsList == null){
            return null;
        }
        List<WeldJoinType> result = new ArrayList<WeldJoinType>();
        for (WeldJoinTypeUI wjt: obsList){
            WeldJoinType wt = getWeldJoinTypeFromUIModel(wjt);
            if (wt!=null){
                result.add(wt);
            }
        }
        return result;
    }

    private void updateWeldJoinTypeFromUIModel(WeldJoinType updWeldJoinType, WeldJoinTypeUI uiModel){
        updWeldJoinType.setType(uiModel.getType());
        updWeldJoinType.setDescription(uiModel.getDescription());
    }
}
