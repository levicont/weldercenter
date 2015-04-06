package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.SteelType;
import com.lvg.weldercenter.services.SteelTypeService;
import com.lvg.weldercenter.ui.entities.SteelTypeUI;
import com.lvg.weldercenter.ui.servicesui.SteelTypeServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 06.04.2015.
 */
public class SteelTypeUIManager implements SteelTypeServiceUI {
    @Autowired
    SteelTypeService steelTypeService;



    @Override
    public SteelType getSteelTypeFromUIModel(SteelTypeUI steelTypeUI) {
        if (steelTypeUI == null)
            return null;
        SteelType st = steelTypeService.get(steelTypeUI.getId());
        if(st != null){
            updateSteelTypeFromUIModel(st, steelTypeUI);
            return st;
        }else {
            st = new SteelType();
            updateSteelTypeFromUIModel(st, steelTypeUI);
            return st;
        }
    }

    private void updateSteelTypeFromUIModel(SteelType updSteelType, SteelTypeUI modelUI){
        updSteelType.setType(modelUI.getType());
        updSteelType.setStGroup(modelUI.getSteelGroup());
    }
}
