package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.WeldDetail;
import com.lvg.weldercenter.ui.entities.WeldDetailUI;

/**
 * Created by Victor on 06.04.2015.
 */
public interface WeldDetailServiceUI {

    public WeldDetail getWeldDetailFromUIModel(WeldDetailUI weldDetailUI);
}
