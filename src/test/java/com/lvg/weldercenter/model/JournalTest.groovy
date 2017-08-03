package com.lvg.weldercenter.model
import com.lvg.weldercenter.models.Journal
import com.lvg.weldercenter.models.PersonalProtocol
import com.lvg.weldercenter.services.JournalService
import com.lvg.weldercenter.services.WelderService
import com.lvg.weldercenter.utils.ModelGenerator
import org.apache.log4j.Logger
import org.apache.maven.model.Model
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import static org.junit.Assert.assertNotNull
/**
 * Created by Victor on 03.08.2017.
 */
class JournalTest extends GenericModelTest{
    private final Logger LOGGER = Logger.getLogger(JournalTest.class)

    @Autowired
    private JournalService journalService
    @Autowired
    private WelderService welderService


    @Test
    @Transactional
    public void allJournalsTest(){
        initDB()
        List<Journal> allJournal = journalService.getAll()
        assert allJournal.size() > 0
        assertNotNull(allJournal)
    }

    @Test
    @Transactional
    public void testPersonalProtocolsCollectionField(){
        initDB()
        List<Journal> allJournal = journalService.getAll()
        assert allJournal.size() > 0
        Journal journal = journalService.get(allJournal.get(0).getJournalId())
        assertNotNull(journal)
        List<PersonalProtocol> ppList = journal.getPersonalProtocols()
        assertNotNull(ppList)
        LOGGER.info("Journal - "+ journal.toString())
        PersonalProtocol pp = ppList.get(0)
        assertNotNull(pp)
        LOGGER.info("PersonalProtocol - "+ pp.toString())

    }

    private void initDB(){
//        def welder1 = ModelGenerator.welder
//        welder1.welderId = welderService.insert(welder1)
//        assert welder1.welderId > 0
//
//        def welder2 = ModelGenerator.welder
//        welder2.welderId = welderService.insert(welder2)
//        assert welder2.welderId > 0

        def journal = ModelGenerator.journal
        journalService.insert(journal)
        assert journal.journalId > 0

    }
}
