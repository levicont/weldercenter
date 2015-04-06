package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.Electrode;
import com.lvg.weldercenter.services.ElectrodeService;
import com.lvg.weldercenter.ui.entities.ElectrodeUI;
import com.lvg.weldercenter.ui.servicesui.ElectrodeServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 06.04.2015.
 */
public class ElectrodeUIManager implements ElectrodeServiceUI {
    @Autowired
    private ElectrodeService electrodeService;

    @Override
    public Electrode getElectrodeFromUIModel(ElectrodeUI electrodeUI) {
        if (electrodeUI == null) {
            return null;
        }
        Electrode el = electrodeService.get(electrodeUI.getId());
        if (el != null){
            updateElectrodeFromUIModel(el, electrodeUI);
            return el;
        }else {
            el = new Electrode();
            updateElectrodeFromUIModel(el, electrodeUI);
            return el;
        }
    }

    private void updateElectrodeFromUIModel(Electrode updElectrode, ElectrodeUI modelUI){
        updElectrode.setType(modelUI.getType());
    }
}
