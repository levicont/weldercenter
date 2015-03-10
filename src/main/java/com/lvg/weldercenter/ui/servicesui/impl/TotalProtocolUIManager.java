package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.TotalProtocol;
import com.lvg.weldercenter.services.TotalProtocolService;
import com.lvg.weldercenter.spring.factories.ServiceFactory;
import com.lvg.weldercenter.spring.factories.ServiceUIFactory;
import com.lvg.weldercenter.ui.entities.TotalProtocolUI;
import com.lvg.weldercenter.ui.servicesui.JournalServiceUI;
import com.lvg.weldercenter.ui.servicesui.TotalProtocolServiceUI;
import com.lvg.weldercenter.ui.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.02.15.
 */
public class TotalProtocolUIManager implements TotalProtocolServiceUI {
    private final Logger LOGGER = Logger.getLogger(TotalProtocolUIManager.class);

    @Autowired
    private TotalProtocolService totalProtocolService;
    @Autowired
    private JournalServiceUI journalServiceUI;


    @Override
    public void saveTotalProtocolUIinDB(TotalProtocolUI totalProtocolUI) {
        if(totalProtocolUI.getJournal()!=null){
            TotalProtocolUI protocolUIFromDB =
                    getTotalProtocolUIByJournalID(totalProtocolUI.getJournal().getId());
            if(protocolUIFromDB!=null){
                totalProtocolUI.setId(protocolUIFromDB.getId());
            }
        }
        if(totalProtocolUI.getId() == 0){
            totalProtocolService.insert(getTotalProtocolFromTotalProtocolUI(totalProtocolUI));
        }else {
            totalProtocolService.update(getTotalProtocolFromTotalProtocolUI(totalProtocolUI));
        }

    }

    @Override
    public TotalProtocol getTotalProtocolFromTotalProtocolUI(TotalProtocolUI totalProtocolUI) {
        TotalProtocol result;
        if (totalProtocolUI.getId() == 0){
            result = new TotalProtocol();
        }else{
            result = totalProtocolService.get(totalProtocolUI.getId());
        }
        result.setIdTotalProtocol(totalProtocolUI.getId());
        result.setNumber(totalProtocolUI.getNumber());
        result.setDateCert(totalProtocolUI.getDateCert());
        result.setJournal(journalServiceUI.getJournalFromJournalUI(totalProtocolUI.getJournal()));
        return result;
    }

    @Override
    public TotalProtocolUI getTotalProtocolUIByJournalID(Long journalID){
        TotalProtocolUI result = null;
        for(TotalProtocol tp : totalProtocolService.getAll()){
            if(tp.getJournal().getJournalId() == journalID) {
                result = new TotalProtocolUI(tp);
                break;
            }
        }
        return  result;
    }

    @Override
    public TotalProtocolUI getTotalProtocolUIByNumberAndDate(String protocolNumber, String dateFormat){
        TotalProtocolUI result = null;
        List<TotalProtocol> totalProtocolList = totalProtocolService.getAll();
        for (TotalProtocol tp : totalProtocolList){
            if(protocolNumber.toLowerCase().equals(tp.getNumber().toLowerCase()) &&
                    dateFormat.equals(DateUtil.format(tp.getDateCert()))){
                result = new TotalProtocolUI(tp);
                break;
            }
        }
        return result;
    }

    @Override
    public TotalProtocolUI getTotalProtocolUIByToStringMethod(String stringName){
        TotalProtocolUI result = null;
        if(stringName.contains("(") && stringName.contains(")")){
            String number = getTotalProtocolNumberFromToStringMethod(stringName);
            String dateString = getTotalProtocolDateFromToStringMethod(stringName);
            result = getTotalProtocolUIByNumberAndDate(number, dateString);
            LOGGER.debug("GET TOTAL PROTOCOL BY NAME: Number: "+number+" DateString: "+dateString);
        }
        return result;

    }

    @Override
    public TotalProtocolUI getTotalProtocolUIByToStringMethod(String stringName, List<TotalProtocolUI> totalProtocolUIList) {
        TotalProtocolUI result = null;
        String protocolNumber = getTotalProtocolNumberFromToStringMethod(stringName);
        String dateFormat = getTotalProtocolDateFromToStringMethod(stringName);
        for (TotalProtocolUI tp : totalProtocolUIList){
            if(protocolNumber.toLowerCase().equals(tp.getNumber().toLowerCase()) &&
                    dateFormat.equals(DateUtil.format(tp.getDateCert()))){
                result = tp;
                break;
            }
        }
        return result;
    }

    private String getTotalProtocolNumberFromToStringMethod(String stringName){
        String result = "";
        if(stringName.contains("(") && stringName.contains(")")){
            String number = stringName.substring(0, stringName.indexOf(" "));
            result = number;
            LOGGER.debug("GET TOTAL PROTOCOL NUMBER BY NAME: Number: "+number);
        }
        return result;
    }

    private String getTotalProtocolDateFromToStringMethod(String stringName){
        String result = "";
        if(stringName.contains("(") && stringName.contains(")")){
            String dateString = stringName.substring(stringName.indexOf("(")+5, stringName.indexOf(")")-3);
            result = dateString;
            LOGGER.debug("GET TOTAL PROTOCOL DATE STRING BY NAME: DateString: "+dateString);
        }
        return result;
    }
}
