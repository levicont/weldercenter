package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.AttestType
import com.lvg.weldercenter.se.models.Journal
import com.lvg.weldercenter.se.models.PersonalProtocol
import com.lvg.weldercenter.se.models.Welder
import org.junit.Test

import javax.persistence.EntityManager
import java.time.LocalDate

class PersonalProtocolTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        def PP_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Welder welder = getWelder()
            Journal journal = getJournal()

            em.persist(welder)
            em.persist(journal)

            PersonalProtocol pp = getPersonalProtocol(welder,journal)
            pp.ndtDocuments.each {em.persist(it)}
            em.persist(pp)
            def weldPattern = getWeldPattern(pp)
            pp.weldPatterns.add(weldPattern)

            em.persist(pp)
            em.persist(weldPattern)
            PP_ID = pp.id
            return em
        }
        assert PP_ID != null

        def chkPP
        callInTransaction {
            def em = EMF.createEntityManager()
            chkPP = em.find(PersonalProtocol.class, PP_ID)

            assert chkPP.ndtDocuments.size() == 3
            assert chkPP.weldPatterns.size() == 1
            assert chkPP.weldPatterns[0].mark == '01'
            assert chkPP.id != null
            assert chkPP.number == '17/001'
            assert chkPP.dateCertification == LocalDate.of(2017,6,1)
            assert chkPP.theoryTest.rating == 'сдано'
            assert chkPP.theoryTest.ticketNumber == '1, 2, 9'
            assert "$chkPP.attestType" == "$AttestType.PRIMARY"

            return em
        }


    }

    @Override
    @Test
    void updateItemTest() {
        def PERSONAL_PROTOCOL_ID

        callInTransaction {
            EntityManager em = EMF.createEntityManager()
            Journal journal = getJournal()
            Welder welder = getWelder()
            em.persist(journal)
            em.persist(welder)

            def pp = getPersonalProtocol(welder, journal)
            pp.ndtDocuments.each {em.persist(it)}
            em.persist(pp)
            PERSONAL_PROTOCOL_ID = pp.id

            assert journal.id != null
            assert welder.id != null
            assert pp.id != null


            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            PersonalProtocol ppUpd = em.find(PersonalProtocol.class, PERSONAL_PROTOCOL_ID)
            ppUpd.number = '17/002'
            em.persist(ppUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            def chkPersonalProtocol = em.find(PersonalProtocol.class, PERSONAL_PROTOCOL_ID)
            assert chkPersonalProtocol.number == '17/002'
            return em
        }
    }

    @Override
    @Test
    void deleteItemTest() {
        def PERSONAL_PROTOCOL_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Journal journal = getJournal()
            Welder welder = getWelder()
            em.persist(journal)
            em.persist(welder)

            PersonalProtocol pp = getPersonalProtocol(welder, journal)
            pp.ndtDocuments.each {em.persist(it)}
            em.persist(pp)
            PERSONAL_PROTOCOL_ID = pp.id
            return em
        }
        assert PERSONAL_PROTOCOL_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            def personalProtocolUpd = em.find(PersonalProtocol.class, PERSONAL_PROTOCOL_ID)
            em.remove(personalProtocolUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            def chkPersonalProtocol = em.find(PersonalProtocol.class, PERSONAL_PROTOCOL_ID)
            assert chkPersonalProtocol == null
            return em
        }

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
