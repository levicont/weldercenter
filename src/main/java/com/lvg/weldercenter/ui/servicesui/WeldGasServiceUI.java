package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.WeldGas;
import com.lvg.weldercenter.ui.entities.WeldGasUI;

/**
 * Created by Victor on 06.04.2015.
 */
public interface WeldGasServiceUI {

    public WeldGas getWeldGasFromUIModel(WeldGasUI weldGasUI);
}
