package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.WeldGas;
import com.lvg.weldercenter.services.WeldGasService;
import com.lvg.weldercenter.ui.entities.WeldGasUI;
import com.lvg.weldercenter.ui.servicesui.WeldGasServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor Levchenko LVG Corp. on 06.04.15.
 */
public class WeldGasUIManager implements WeldGasServiceUI {
    @Autowired
    private WeldGasService weldGasService;

    @Override
    public WeldGas getWeldGasFromUIModel(WeldGasUI weldGasUI) {
        if (weldGasUI == null) {
            return null;
        }
        WeldGas wg = weldGasService.get(weldGasUI.getId());
        if (wg != null){
            updateWeldGasFromUIModel(wg,weldGasUI);
        }else {
            wg = new WeldGas();
            updateWeldGasFromUIModel(wg,weldGasUI);
        }
        return wg;
    }

    private void updateWeldGasFromUIModel(WeldGas updWeldGas, WeldGasUI modelUI){
        updWeldGas.setType(modelUI.getType());
    }
}
