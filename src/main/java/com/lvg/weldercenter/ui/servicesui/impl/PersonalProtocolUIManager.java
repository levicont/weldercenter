package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.PersonalProtocol;
import com.lvg.weldercenter.services.PersonalProtocolService;
import com.lvg.weldercenter.ui.entities.JournalUI;
import com.lvg.weldercenter.ui.entities.PersonalProtocolUI;
import com.lvg.weldercenter.ui.entities.TotalProtocolUI;
import com.lvg.weldercenter.ui.entities.WelderUI;
import com.lvg.weldercenter.ui.servicesui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Victor on 11.03.2015.
 */
public class PersonalProtocolUIManager implements PersonalProtocolServiceUI {

    @Autowired
    private PersonalProtocolService personalProtocolService;
    @Autowired
    private JournalServiceUI journalServiceUI;
    @Autowired
    private WelderServiceUI welderServiceUI;
    @Autowired
    private TheoryTestServiceUI theoryTestServiceUI;
    @Autowired
    private NDTDocumentServiceUI ndtDocumentServiceUI;
    @Autowired
    private ResolutionCertificationServiceUI resolutionCertificationServiceUI;
//    @Autowired
//    private WeldPatternServiceUI weldPatternServiceUI;


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

    @Override
    public PersonalProtocol getPersonalProtocolFromUIModel(PersonalProtocolUI personalProtocolUI) {
        if (personalProtocolUI == null) {
            return null;
        }
        PersonalProtocol pp = personalProtocolService.get(personalProtocolUI.getId());
        if (pp != null){
            updatePersonalProtocolFromUIModel(pp, personalProtocolUI);
        }else {
            pp = new PersonalProtocol();
            updatePersonalProtocolFromUIModel(pp, personalProtocolUI);
        }
        return pp;

    }

    private void updatePersonalProtocolFromUIModel(PersonalProtocol updPersonalPrptocol, PersonalProtocolUI modelUI){
        updPersonalPrptocol.setNumber(modelUI.getNumber());
        updPersonalPrptocol.setDatePeriodicalCert(modelUI.getDatePeriodicalCert());
        updPersonalPrptocol.setJournal(journalServiceUI.getJournalFromJournalUI(modelUI.getJournal()));
        updPersonalPrptocol.setWelder(welderServiceUI.getWelderFromWelderUI(modelUI.getWelder()));
        updPersonalPrptocol.setTheoryTest(theoryTestServiceUI.getTheoryTestFromUIModel(modelUI.getTheoryTest()));
        updPersonalPrptocol.setNdtDocuments(ndtDocumentServiceUI.getNDTDocumentListFromObsList(modelUI.getNdtDocuments()));
        updPersonalPrptocol.setResolutionCertification(resolutionCertificationServiceUI.getResolutionCertFromUIModel(
                modelUI.getResolutionCertification()
        ));
       // updPersonalPrptocol.setWeldPatterns(weldPatternServiceUI.getWeldPatternListFromObsList(modelUI.getWeldPatterns()));

    }
}
