package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.AttestType
import com.lvg.weldercenter.se.models.Journal
import com.lvg.weldercenter.se.models.PersonalProtocol
import com.lvg.weldercenter.se.models.Welder
import com.lvg.weldercenter.se.services.JournalService
import com.lvg.weldercenter.se.services.NDTDocumentService
import com.lvg.weldercenter.se.services.PersonalProtocolService
import com.lvg.weldercenter.se.services.WeldPatternService
import com.lvg.weldercenter.se.services.WelderService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDate

class PersonalProtocolTest extends GenericModelTest{
    @Autowired
    PersonalProtocolService personalProtocolService
    @Autowired
    WelderService welderService
    @Autowired
    JournalService journalService
    @Autowired
    WeldPatternService weldPatternService

    @Override
    @Test
    void insertItemTest() {
        def PP_ID
       
            Welder welder = getWelder()
            Journal journal = getJournal()

            welderService.save(welder)
            journalService.save(journal)

            PersonalProtocol pp = getPersonalProtocol(welder,journal)
            personalProtocolService.save(pp)
            def weldPattern = getWeldPattern(pp)
            pp.weldPatterns.add(weldPattern)

            personalProtocolService.save(pp)
            weldPatternService.save(weldPattern)
            PP_ID = pp.id

        assert PP_ID != null

        def chkPP

            chkPP = personalProtocolService.get(PP_ID)

            assert chkPP.ndtDocuments.size() == 3
            assert chkPP.weldPatterns.size() == 1
            assert chkPP.weldPatterns[0].mark == '01'
            assert chkPP.id != null
            assert chkPP.number == '17/001'
            assert chkPP.dateCertification == LocalDate.of(2017,6,1)
            assert chkPP.theoryTest.rating == 'сдано'
            assert chkPP.theoryTest.ticketNumber == '1, 2, 9'
            assert "$chkPP.attestType" == "$AttestType.PRIMARY"

    }

    @Override
    @Test
    void updateItemTest() {
        def PERSONAL_PROTOCOL_ID


            Journal journal = getJournal()
            Welder welder = getWelder()
            journalService.save(journal)
            welderService.save(welder)

            def pp = getPersonalProtocol(welder, journal)
            personalProtocolService.save(pp)
            PERSONAL_PROTOCOL_ID = pp.id

            assert journal.id != null
            assert welder.id != null
            assert pp.id != null

            PersonalProtocol ppUpd = personalProtocolService.get(PERSONAL_PROTOCOL_ID)
            ppUpd.number = '17/002'
            personalProtocolService.save(ppUpd)


            def chkPersonalProtocol = personalProtocolService.get(PERSONAL_PROTOCOL_ID)
            assert chkPersonalProtocol.number == '17/002'
    }

    @Override
    @Test
    void deleteItemTest() {
        def PERSONAL_PROTOCOL_ID

            Journal journal = getJournal()
            Welder welder = getWelder()
            journalService.save(journal)
            welderService.save(welder)

            PersonalProtocol pp = getPersonalProtocol(welder, journal)
            personalProtocolService.save(pp)
            PERSONAL_PROTOCOL_ID = pp.id

        assert PERSONAL_PROTOCOL_ID != null

            def personalProtocolUpd = personalProtocolService.get(PERSONAL_PROTOCOL_ID)
            personalProtocolService.delete(personalProtocolUpd)

            def chkPersonalProtocol = personalProtocolService.get(PERSONAL_PROTOCOL_ID)
            assert chkPersonalProtocol == null
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
