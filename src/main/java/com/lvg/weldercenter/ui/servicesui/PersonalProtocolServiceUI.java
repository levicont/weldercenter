package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.PersonalProtocol;
import com.lvg.weldercenter.ui.entities.JournalUI;
import com.lvg.weldercenter.ui.entities.PersonalProtocolUI;
import com.lvg.weldercenter.ui.entities.TotalProtocolUI;

/**
 * Created by Victor on 11.03.2015.
 */
public interface PersonalProtocolServiceUI {
    public PersonalProtocolUI getPersonalProtocolUI(TotalProtocolUI totalProtocolUI, String welderFullName);
    public PersonalProtocolUI getPersonalProtocolUIFromDB(TotalProtocolUI totalProtocolUI, String welderFullName);
    public PersonalProtocol getPersonalProtocolFromUIModel(PersonalProtocolUI personalProtocolUI);
    public void savePersonalProtocolsFromJournalUI(JournalUI journalUI);
}
