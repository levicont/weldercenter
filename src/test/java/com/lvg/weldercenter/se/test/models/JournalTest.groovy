package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Journal
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction
import java.time.LocalDate

/**
 * Created by Victor on 06.10.2017.
 */
class JournalTest extends GenericModelTest{


    @Override
    @Test
    void insertItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Journal journal = getJournal()
        em.persist(journal)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def list = em.createQuery('select j from Journal j').getResultList()
        tx.commit()

        assert list.size() == 1
        journal = list.get(0)


        assert journal.id != null
        assert journal.number == '17/001'
        assert journal.dateBegin == LocalDate.of(2017, 05, 25)
        assert journal.dateEnd == journal.dateBegin.plusWeeks(1)
    }

    @Override
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Journal journal = getJournal()
        em.persist(journal)
        tx.commit()

        assert journal.id != null
        def JOURNAL_ID = journal.id
        tx.begin()
        em = EMF.createEntityManager()
        Journal journalUpd = em.find(Journal.class, JOURNAL_ID)
        journalUpd.number = '17/002'
        em.persist(journalUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Journal chkJournal = em.find(Journal.class, JOURNAL_ID)
        assert chkJournal.number == '17/002'
        tx.commit()
    }

    @Override
    @Test
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Journal journal = getJournal()
        em.persist(journal)
        tx.commit()

        assert journal.id != null
        def JOURNAL_ID = journal.id

        tx.begin()
        em = EMF.createEntityManager()
        Journal journalUpd = em.find(Journal.class, JOURNAL_ID)
        em.remove(journalUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Journal chkJournal = em.find(Journal.class, JOURNAL_ID)
        assert chkJournal == null
        tx.commit()

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
