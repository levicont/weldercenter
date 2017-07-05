package com.lvg.weldercenter.ui.control;

import com.lvg.weldercenter.models.Journal;
import com.lvg.weldercenter.services.JournalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

/**
 * Created by Victor on 05.07.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context-db-test.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ProtocolControllerTest {

    @Autowired
    private JournalService journalService;

    @Test
    public void testConnection(){
        List<Journal> allJournals = journalService.getAll();
        System.out.println(allJournals.size());
    }

}
