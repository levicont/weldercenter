package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.Journal;
import com.lvg.weldercenter.ui.entities.JournalUI;

/**
 * Created by Victor Levchenko LVG Corp. on 19.02.15.
 */
public interface JournalServiceUI {

    public Journal getJournalFromJournalUI(JournalUI journalUI);
}
