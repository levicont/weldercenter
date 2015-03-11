package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.ui.entities.JournalUI;
import com.lvg.weldercenter.ui.entities.PersonalProtocolUI;
import com.lvg.weldercenter.ui.entities.TotalProtocolUI;
import com.lvg.weldercenter.ui.entities.WelderUI;
import com.lvg.weldercenter.ui.servicesui.PersonalProtocolServiceUI;

/**
 * Created by Victor on 11.03.2015.
 */
public class PersonalProtocolUIManager implements PersonalProtocolServiceUI {

    @Override
    public PersonalProtocolUI getPersonalProtocolUI(TotalProtocolUI totalProtocolUI, String welderFullName) {
        PersonalProtocolUI result = null;
        JournalUI journalUI = totalProtocolUI.getJournal();
        if(journalUI!=null){
            for(WelderUI w : journalUI.getWelders()){
                String welderSurnameNameSecname = w.getSurnameNameSecname();
                if(welderFullName.trim().equals(welderSurnameNameSecname)){
                    result = new PersonalProtocolUI(w,journalUI);
                    break;
                }
            }
        }
        return result;
    }
}
