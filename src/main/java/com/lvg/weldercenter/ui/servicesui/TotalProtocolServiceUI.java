package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.TotalProtocol;
import com.lvg.weldercenter.ui.entities.TotalProtocolUI;

import java.util.Date;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 19.02.15.
 */
public interface TotalProtocolServiceUI {

    /**
     * If totalProtocol is new record, than insert in DB,
     * else update totalProtocol record in DB.
     * @param totalProtocolUI
     */
    public void saveTotalProtocolUIinDB(TotalProtocolUI totalProtocolUI);

    public TotalProtocolUI getTotalProtocolUIByJournalID(Long journalID);
    public TotalProtocol getTotalProtocolFromTotalProtocolUI(TotalProtocolUI totalProtocolUI);
    public TotalProtocolUI getTotalProtocolUIByNumberAndDate(String protocolNumber, String dateFormat);
    public TotalProtocolUI getTotalProtocolUIByToStringMethod(String stringName);
    public TotalProtocolUI getTotalProtocolUIByToStringMethod(String stringName, List<TotalProtocolUI> totalProtocolUIList);

}
