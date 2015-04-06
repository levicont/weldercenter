package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.WeldWire;
import com.lvg.weldercenter.services.WeldWireService;
import com.lvg.weldercenter.ui.entities.WeldWireUI;
import com.lvg.weldercenter.ui.servicesui.WeldWireServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 06.04.2015.
 */
public class WeldWireUIManager implements WeldWireServiceUI {
    @Autowired
    private WeldWireService weldWireService;

    @Override
    public WeldWire getWeldWireFromUIModel(WeldWireUI weldWireUI) {
        if (weldWireUI == null) {
            return null;
        }
        WeldWire ww = weldWireService.get(weldWireUI.getId());
        if (ww != null){
            updateWeldWireFromUIModel(ww, weldWireUI);
        }else {
            ww = new WeldWire();
            updateWeldWireFromUIModel(ww, weldWireUI);
        }
        return ww;
    }

    private void updateWeldWireFromUIModel(WeldWire updWeldWire, WeldWireUI modelUI ){
        updWeldWire.setType(modelUI.getType());
    }
}
