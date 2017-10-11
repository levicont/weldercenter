package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.AttestType
import com.lvg.weldercenter.se.models.Journal
import com.lvg.weldercenter.se.models.PersonalProtocol
import com.lvg.weldercenter.se.models.Welder
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction
import java.time.LocalDate

class PersonalProtocolTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Welder welder = getWelder()
        Journal journal = getJournal()


        em.persist(welder)
        em.persist(journal)

        PersonalProtocol pp = getPersonalProtocol(welder,journal)
        pp.ndtDocuments.each {em.persist(it)}

        em.persist(pp)

        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def list = em.createQuery('select p from PersonalProtocol p').getResultList()
        tx.commit()

        assert list.size() == 1
        pp = list.get(0)


        assert pp.id != null
        assert pp.number == '17/001'
        assert pp.dateCertification == LocalDate.of(2017,6,1)
        assert pp.theoryTest.rating == 'сдано'
        assert pp.theoryTest.ticketNumber == '1, 2, 9'
        assert pp.ndtDocuments.size() == 3

        assert "$pp.attestType" == "$AttestType.PRIMARY"
    }

    @Override
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Journal journal = getJournal()
        Welder welder = getWelder()
        em.persist(journal)
        em.persist(welder)

        def pp = getPersonalProtocol(welder, journal)
        pp.ndtDocuments.each {em.persist(it)}
        em.persist(pp)

        tx.commit()

        assert journal.id != null
        assert welder.id != null
        assert pp.id != null

        def PERSONAL_PROTOCOL_ID = pp.id

        tx.begin()
        em = EMF.createEntityManager()
        def ppUpd = em.find(PersonalProtocol.class, PERSONAL_PROTOCOL_ID)
        ppUpd.number = '17/002'

        em.persist(ppUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def chkPersonalProtocol = em.find(PersonalProtocol.class, PERSONAL_PROTOCOL_ID)
        assert chkPersonalProtocol.number == '17/002'
        tx.commit()
    }

    @Override
    @Test
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Journal journal = getJournal()
        Welder welder = getWelder()
        em.persist(journal)
        em.persist(welder)

        def pp = getPersonalProtocol(welder, journal)
        pp.ndtDocuments.each {em.persist(it)}
        em.persist(pp)

        tx.commit()

        assert pp.id != null
        def PERSONAL_PROTOCOL_ID = pp.id

        tx.begin()
        em = EMF.createEntityManager()
        def personalProtocolUpd = em.find(PersonalProtocol.class, PERSONAL_PROTOCOL_ID)
        em.remove(personalProtocolUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def chkPersonalProtocol = em.find(PersonalProtocol.class, PERSONAL_PROTOCOL_ID)
        assert chkPersonalProtocol == null
        tx.commit()
    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def welder = getWelder()
        welder.id = 100
        def welder2 = getWelder()
        welder2.id = 101

        def journal = getJournal()
        journal.id = 100
        def journal2 = getJournal()
        journal2.id = 101


        def pp1 = getPersonalProtocol(welder, journal)
        def pp2 = getPersonalProtocol(welder, journal)

        assert pp1 == pp2

        pp1.id = 100
        pp2.id = 101

        assert pp1 != pp2

        def list = new HashSet<PersonalProtocol>()
        list.add(pp1)
        pp2.id = 100
        list.add(pp2)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def welder = getWelder()
        welder.id = 100

        def journal = getJournal()
        journal.id = 100

        def pp = getPersonalProtocol(welder,journal)

        assert pp.toString() == "17/001 2017-06-01 Иванов Иван Иванович"
    }
}
