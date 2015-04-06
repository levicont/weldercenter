package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.WeldPosition;
import com.lvg.weldercenter.services.WeldPositionService;
import com.lvg.weldercenter.ui.entities.WeldPositionUI;
import com.lvg.weldercenter.ui.servicesui.WeldPositionServiceUI;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 06.04.2015.
 */
public class WeldPositionUIManager implements WeldPositionServiceUI {
    @Autowired
    private WeldPositionService weldPositionService;


    @Override
    public WeldPosition getWeldPositionFromUIModel(WeldPositionUI weldPositionUI) {
        if (weldPositionUI == null)
            return null;
        WeldPosition wp = weldPositionService.get(weldPositionUI.getId());
        if (wp != null){
            updateWeldPositionFromUIModel(wp, weldPositionUI);
            return wp;
        }else {
            wp = new WeldPosition();
            updateWeldPositionFromUIModel(wp, weldPositionUI);
            return wp;
        }
    }

    @Override
    public List<WeldPosition> getWeldPositionListFromObsList(ObservableList<WeldPositionUI> obsList) {
        if (obsList == null)
            return null;
        List<WeldPosition> weldPositions = new ArrayList<WeldPosition>();
        for (WeldPositionUI wpUI : obsList){
            if(getWeldPositionFromUIModel(wpUI)!= null){
                weldPositions.add(getWeldPositionFromUIModel(wpUI));
            }
        }
        return weldPositions;
    }

    private void updateWeldPositionFromUIModel(WeldPosition updWeldPosition, WeldPositionUI modelUI){
        updWeldPosition.setType(modelUI.getType());
        updWeldPosition.setCode(modelUI.getCode());
    }
}
