package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Journal
import org.junit.Test

import java.time.LocalDate

class JournalTest extends GenericModelTest{


    @Override
    @Test
    void insertItemTest() {
        def JOURNAL_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Journal journal = getJournal()
            em.persist(journal)
            JOURNAL_ID = journal.id
            return em
        }
        assert JOURNAL_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            def chkJournal = em.find(Journal.class, JOURNAL_ID)
            assert chkJournal.id != null
            assert chkJournal.number == '17/001'
            assert chkJournal.dateBegin == LocalDate.of(2017, 05, 25)
            assert chkJournal.dateEnd == journal.dateBegin.plusWeeks(1)
            return em
        }
    }

    @Override
    @Test
    void updateItemTest() {
        def JOURNAL_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Journal journal = getJournal()
            em.persist(journal)
            JOURNAL_ID = journal.id
            return em
        }
        assert JOURNAL_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Journal journalUpd = em.find(Journal.class, JOURNAL_ID)
            journalUpd.number = '17/002'
            em.persist(journalUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Journal chkJournal = em.find(Journal.class, JOURNAL_ID)
            assert chkJournal.number == '17/002'
            return em
        }
    }

    @Override
    @Test
    void deleteItemTest() {
        def JOURNAL_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Journal journal = getJournal()
            em.persist(journal)
            JOURNAL_ID = journal.id
            return em
        }
        assert JOURNAL_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Journal journalUpd = em.find(Journal.class, JOURNAL_ID)
            em.remove(journalUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Journal chkJournal = em.find(Journal.class, JOURNAL_ID)
            assert chkJournal == null
            return em
        }

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
