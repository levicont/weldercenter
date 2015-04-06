package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.WeldDetail;
import com.lvg.weldercenter.services.WeldDetailService;
import com.lvg.weldercenter.ui.entities.WeldDetailUI;
import com.lvg.weldercenter.ui.servicesui.WeldDetailServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 06.04.2015.
 */
public class WeldDetailUIManager implements WeldDetailServiceUI {

    @Autowired
    private WeldDetailService weldDetailService;

    @Override
    public WeldDetail getWeldDetailFromUIModel(WeldDetailUI weldDetailUI) {
        if (weldDetailUI == null)
            return null;
        WeldDetail wd = weldDetailService.get(weldDetailUI.getId());
        if (wd!= null){
            updateWeldDetailFromUIModel(wd,weldDetailUI);
            return wd;
        }else {
            wd = new WeldDetail();
            updateWeldDetailFromUIModel(wd,weldDetailUI);
            return wd;
        }

    }

    private void updateWeldDetailFromUIModel(WeldDetail updWeldDetail, WeldDetailUI modelWeldDetailUI){
        updWeldDetail.setType(modelWeldDetailUI.getType());
        updWeldDetail.setCode(modelWeldDetailUI.getCode());
    }
}
