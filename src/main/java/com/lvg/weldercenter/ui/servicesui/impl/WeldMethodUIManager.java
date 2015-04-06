package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.WeldMethod;
import com.lvg.weldercenter.services.WeldMethodService;
import com.lvg.weldercenter.ui.entities.WeldMethodUI;
import com.lvg.weldercenter.ui.servicesui.WeldMethodServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 06.04.2015.
 */
public class WeldMethodUIManager implements WeldMethodServiceUI {

    @Autowired
    private WeldMethodService weldMethodService;

    @Override
    public WeldMethod getWeldMethodFromUI(WeldMethodUI weldMethodUI) {
        if (weldMethodUI != null){
            return null;
        }
        WeldMethod wm = weldMethodService.get(weldMethodUI.getId());
        if (wm != null){
            updateWeldMethodFromUIModel(wm, weldMethodUI);
            return wm;
        }else {
            wm = new WeldMethod();
            updateWeldMethodFromUIModel(wm,weldMethodUI);
            return wm;
        }

    }

    private void updateWeldMethodFromUIModel(WeldMethod updWeldMethod, WeldMethodUI modelUI){
        updWeldMethod.setCode(modelUI.getCode());
        updWeldMethod.setName(modelUI.getName());
    }
}
