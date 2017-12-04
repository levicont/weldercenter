package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.Journal
import com.lvg.weldercenter.se.services.JournalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import java.time.LocalDate

@Transactional
class JournalServiceTest extends GenericServiceTest{

    @Autowired
    JournalService journalService

    @Override
    void saveTest() {
        def JOURNAL_ID
        def journal = getJournal()
        journal = journalService.save(journal)
        JOURNAL_ID = journal.id
        assert JOURNAL_ID != null

        Journal updJournal = journalService.get(JOURNAL_ID)
        assert updJournal != null
        assert updJournal instanceof Journal
        updJournal.number = '17-100'
        updJournal.dateBegin = LocalDate.of(2017, 03, 15)
        journalService.save(journal)

        Journal chkJournal = journalService.get(JOURNAL_ID)
        assert chkJournal != null
        assert chkJournal instanceof Journal
        assert chkJournal.number == '17-100'
        assert chkJournal.dateBegin == LocalDate.of(2017, 03, 15)
    }

    @Override
    void deleteTest() {
        def JOURNAL_ID
        def journal = getJournal()
        journal = journalService.save(journal)
        JOURNAL_ID = journal.id
        assert JOURNAL_ID != null

        Journal delJournal = journalService.get(JOURNAL_ID)
        assert delJournal != null
        assert delJournal instanceof Journal
        journalService.delete(journal)

        Journal chkJournal = journalService.get(JOURNAL_ID)
        assert chkJournal == null
    }

    @Override
    void getTest() {
        def JOURNAL_ID
        def journal = getJournal()
        journal = journalService.save(journal)
        JOURNAL_ID = journal.id
        assert JOURNAL_ID != null

        Journal chkJournal = journalService.get(JOURNAL_ID)
        assert chkJournal != null
        assert chkJournal instanceof Journal
    }

    @Override
    void getAllTest() {
        def JOURNAL_ID
        def journal = getJournal()
        journal = journalService.save(journal)
        JOURNAL_ID = journal.id
        assert JOURNAL_ID != null

        def list = journalService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }

    @Override
    void countTest() {
        def JOURNAL_ID
        def journal = getJournal()
        journal = journalService.save(journal)
        JOURNAL_ID = journal.id
        assert JOURNAL_ID != null

        def count = journalService.count()
        assert count != null
        assert count instanceof Long
        assert count == 1
    }
}
