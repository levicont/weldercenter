package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.Welder;
import com.lvg.weldercenter.ui.entities.WelderUI;

/**
 * Created by Victor Levchenko LVG Corp. on 19.02.15.
 */
public interface WelderServiceUI  {

    public Welder getWelderFromWelderUI(WelderUI welderUI);
}
