package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.ui.entities.PersonalProtocolUI;
import com.lvg.weldercenter.ui.entities.TotalProtocolUI;

/**
 * Created by Victor on 11.03.2015.
 */
public interface PersonalProtocolServiceUI {
    public PersonalProtocolUI getPersonalProtocolUI(TotalProtocolUI totalProtocolUI, String welderFullName);
}
