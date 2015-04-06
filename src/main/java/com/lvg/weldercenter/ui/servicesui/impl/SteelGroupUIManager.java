package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.SteelGroup;
import com.lvg.weldercenter.services.SteelGroupService;
import com.lvg.weldercenter.ui.entities.SteelGroupUI;
import com.lvg.weldercenter.ui.servicesui.SteelGroupServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 06.04.2015.
 */
public class SteelGroupUIManager implements SteelGroupServiceUI {

    @Autowired
    SteelGroupService steelGroupService;

    @Override
    public SteelGroup getSteelGroupFromUIModel(SteelGroupUI steelGroupUI) {
        if (steelGroupUI == null)
            return null;
        SteelGroup sg = steelGroupService.get(steelGroupUI.getId());
        if (sg != null){
            updateSteelGroupFromUIModel(sg,steelGroupUI);
            return sg;
        }else {
            sg = new SteelGroup();
            updateSteelGroupFromUIModel(sg, steelGroupUI);
            return sg;
        }
    }

    private void updateSteelGroupFromUIModel(SteelGroup updSteelGroup, SteelGroupUI modelUI){
        updSteelGroup.setStGroup(modelUI.getGroup());
        updSteelGroup.setDescription(modelUI.getDescription());
    }
}
