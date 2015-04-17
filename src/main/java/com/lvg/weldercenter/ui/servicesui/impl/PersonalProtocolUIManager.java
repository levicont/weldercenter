package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.*;
import com.lvg.weldercenter.services.*;
import com.lvg.weldercenter.ui.entities.JournalUI;
import com.lvg.weldercenter.ui.entities.PersonalProtocolUI;
import com.lvg.weldercenter.ui.entities.TotalProtocolUI;
import com.lvg.weldercenter.ui.entities.WelderUI;
import com.lvg.weldercenter.ui.servicesui.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 11.03.2015.
 */
public class PersonalProtocolUIManager implements PersonalProtocolServiceUI {
    private static final Logger LOGGER = Logger.getLogger(PersonalProtocolUIManager.class);

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
    @Autowired
    private ResolutionCertificationService resolutionCertificationService;
    @Autowired
    private TheoryTestService theoryTestService;
    @Autowired
    private JournalService journalService;
    @Autowired
    private WelderService welderService;
    @Autowired
    private WeldPatternServiceUI weldPatternServiceUI;


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

    @Override
    public void savePersonalProtocolsFromJournalUI(JournalUI journalUI) {
        if (journalUI == null)
            return;
        Journal journal = journalService.get(journalUI.getId());
        List<Welder> welders = null;
        List<Welder> weldersCopy = new ArrayList<Welder>();
        if (journal!= null){
            welders = journal.getWelders();
            for (Welder w: welders){
                weldersCopy.add(w);
            }
            for (Welder w: welders){
                if (getPersonalProtocolByJournalWelder(journal,w)!=null){
                    weldersCopy.remove(w);
                }
            }
            for (Welder w : weldersCopy){
                PersonalProtocol pp = createPersonalProtocol(journal,w);
                if (pp != null)
                    personalProtocolService.insert(pp);
            }
        }else {
            return;
        }
    }

    @Override
    public void savePersonalProtocolUIinDB(PersonalProtocolUI personalProtocolUI) {
        PersonalProtocol pp = getPersonalProtocolFromUIModel(personalProtocolUI);
        if (pp == null){
            return;
        }
        ResolutionCertification rs;
        TheoryTest tt;
        if (pp.getPersonalProtocolId()!=null){
            PersonalProtocol ppTempory = personalProtocolService.get(pp.getPersonalProtocolId());
            rs = ppTempory.getResolutionCertification();
            tt = ppTempory.getTheoryTest();
            if (rs!=null){
                resolutionCertificationService.delete(rs);
            }
            if (tt!=null){
                theoryTestService.delete(tt);
            }
        }

        if (pp.getPersonalProtocolId()!=null){
            personalProtocolService.update(pp);
            LOGGER.debug("SAVE_PERSONAL_PROTOCOL_UI_IN_DB: personal protocol is updated");
        }else {
            personalProtocolService.insert(pp);
            LOGGER.debug("SAVE_PERSONAL_PROTOCOL_UI_IN_DB: personal protocol is inserted");
        }
    }

    private PersonalProtocol getPersonalProtocolByJournalWelder(Journal journal, Welder welder){
        if(journal == null || welder == null){
            return null;
        }
        for (PersonalProtocol pp : personalProtocolService.getAll()){
            if (pp.getWelder() == null || pp.getJournal() == null)
                break;
            else {
                if (pp.getJournal().equals(journal) && pp.getWelder().equals(welder))
                    return pp;
            }
        }
        return null;
    }

    private PersonalProtocol createPersonalProtocol(Journal journal, Welder welder){
        if (journal == null || welder == null)
            return null;
        PersonalProtocol result = new PersonalProtocol();
        result.setJournal(journal);
        result.setWelder(welder);
        result.setNumber(journal.getNumber());
        if (journal.getDateEnd()!= null)
            result.setDatePeriodicalCert(journal.getDateEnd());
        return result;
    }

    private void updatePersonalProtocolFromUIModel(PersonalProtocol updPersonalProtocol, PersonalProtocolUI modelUI){
        updPersonalProtocol.setNumber(modelUI.getNumber());
        updPersonalProtocol.setDatePeriodicalCert(modelUI.getDatePeriodicalCert());
        updPersonalProtocol.setJournal(journalServiceUI.getJournalFromJournalUI(modelUI.getJournal()));
        updPersonalProtocol.setWelder(welderServiceUI.getWelderFromWelderUI(modelUI.getWelder()));
        updPersonalProtocol.setTheoryTest(theoryTestServiceUI.getTheoryTestFromUIModel(modelUI.getTheoryTest()));
        updPersonalProtocol.setNdtDocuments(ndtDocumentServiceUI.getNDTDocumentListFromObsList(modelUI.getNdtDocuments()));
        updPersonalProtocol.setResolutionCertification(resolutionCertificationServiceUI.getResolutionCertFromUIModel(
                modelUI.getResolutionCertification()
        ));
        updPersonalProtocol.setWeldPatterns(weldPatternServiceUI.getWeldPatternListFromObsList(modelUI.getWeldPatterns()));

    }

}
