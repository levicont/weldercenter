package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.Electrode;
import com.lvg.weldercenter.ui.entities.ElectrodeUI;

/**
 * Created by Victor on 06.04.2015.
 */
public interface ElectrodeServiceUI {
    public Electrode getElectrodeFromUIModel(ElectrodeUI electrodeUI);
}
