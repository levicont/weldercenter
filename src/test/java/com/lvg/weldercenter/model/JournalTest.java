package com.lvg.weldercenter.model;

import com.lvg.weldercenter.models.Journal;
import com.lvg.weldercenter.models.PersonalProtocol;
import com.lvg.weldercenter.services.JournalService;
import org.hibernate.Hibernate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor on 05.07.2017.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context-db-test.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JournalTest {
    @Autowired
    private JournalService journalService;


    @Test
    public void allJournalsTest(){
        List<Journal> allJournal = journalService.getAll();
        assertNotNull(allJournal);
    }

    @Test
    public void testPersonalProtocolsCollectionField(){
        List<Journal> allJournal = journalService.getAll();

        Journal journal = journalService.get(allJournal.get(0).getJournalId());
        assertNotNull(journal);
        List<PersonalProtocol> ppList = journal.getPersonalProtocols();
        assertNotNull(ppList);

        PersonalProtocol pp = ppList.get(0);
        assertNotNull(pp);


    }
}
