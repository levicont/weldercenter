package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Journal
import com.lvg.weldercenter.se.services.JournalService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDate

class JournalTest extends GenericModelTest{
    @Autowired
    JournalService service

    @Override
    @Test
    void insertItemTest() {
        def JOURNAL_ID

            Journal journal = getJournal()
            service.save(journal)
            JOURNAL_ID = journal.id

        assert JOURNAL_ID != null


            def chkJournal = service.get(JOURNAL_ID)
            assert chkJournal.id != null
            assert chkJournal.number == '17/001'
            assert chkJournal.dateBegin == LocalDate.of(2017, 05, 25)
            assert chkJournal.dateEnd == journal.dateBegin.plusWeeks(1)

    }

    @Override
    @Test
    void updateItemTest() {
        def JOURNAL_ID

            Journal journal = getJournal()
            service.save(journal)
            JOURNAL_ID = journal.id

        assert JOURNAL_ID != null


            Journal journalUpd = service.get(JOURNAL_ID)
            journalUpd.number = '17/002'
            service.save(journalUpd)



            Journal chkJournal = service.get(JOURNAL_ID)
            assert chkJournal.number == '17/002'

    }

    @Override
    @Test
    void deleteItemTest() {
        def JOURNAL_ID

            Journal journal = getJournal()
            service.save(journal)
            JOURNAL_ID = journal.id

        assert JOURNAL_ID != null


            Journal journalUpd = service.get(JOURNAL_ID)
            service.delete(journalUpd)

            Journal chkJournal = service.get(JOURNAL_ID)
            assert chkJournal == null

    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def journal2 = getJournal()
        def journal1 = getJournal()

        assert journal2 == journal1

        journal2.id = 100
        journal1.id = 101

        assert journal2 != journal1

        def list = new HashSet<Journal>()
        list.add(journal2)
        journal1.id = 100
        list.add(journal1)
        assert list.size() == 1

    }

    @Override
    @Test
    void toStringTest() {
        def journal = getJournal()
        assert journal.toString() == "Журнал №17/001 от 2017-05-25"
    }
}
