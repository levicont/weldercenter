package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.WeldWire;
import com.lvg.weldercenter.ui.entities.WeldWireUI;

/**
 * Created by Victor on 06.04.2015.
 */
public interface WeldWireServiceUI {

    public WeldWire getWeldWireFromUIModel(WeldWireUI weldWireUI);
}
