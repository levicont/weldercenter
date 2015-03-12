package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.PersonalProtocol;
import com.lvg.weldercenter.services.PersonalProtocolService;
import com.lvg.weldercenter.ui.entities.JournalUI;
import com.lvg.weldercenter.ui.entities.PersonalProtocolUI;
import com.lvg.weldercenter.ui.entities.TotalProtocolUI;
import com.lvg.weldercenter.ui.entities.WelderUI;
import com.lvg.weldercenter.ui.servicesui.PersonalProtocolServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Victor on 11.03.2015.
 */
public class PersonalProtocolUIManager implements PersonalProtocolServiceUI {

    @Autowired
    private PersonalProtocolService personalProtocolService;

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

    @Override
    public PersonalProtocolUI getPersonalProtocolUIFromDB(TotalProtocolUI totalProtocolUI, String welderFullName) {
        PersonalProtocolUI result = null;
        List<PersonalProtocol> ppList = personalProtocolService.getAll();
        JournalUI journalUI = totalProtocolUI.getJournal();
        if(journalUI!=null){
            for (PersonalProtocol pp : ppList){
                String welderSurnameNameSecname = new WelderUI(pp.getWelder()).getSurnameNameSecname();
                if(welderFullName.trim().equals(welderSurnameNameSecname) && journalUI.getId()==pp.getJournal().getJournalId()){
                    return new PersonalProtocolUI(pp);
                }
            }
        }
        return result;
    }
}
