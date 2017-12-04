package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.PersonalProtocol
import com.lvg.weldercenter.se.services.JournalService
import com.lvg.weldercenter.se.services.PersonalProtocolService
import com.lvg.weldercenter.se.services.WelderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class PersonalProtocolServiceTest extends GenericServiceTest{

    @Autowired
    PersonalProtocolService personalProtocolService
    @Autowired
    WelderService welderService
    @Autowired
    JournalService journalService

    @Override
    void saveTest() {
        def PERSONAL_PROTOCOL_ID
        def welder = welderService.save(getWelder())
        def journal = journalService.save(getJournal())
        def personalProtocol = getPersonalProtocol(welder, journal)
        personalProtocol = personalProtocolService.save(personalProtocol)
        PERSONAL_PROTOCOL_ID = personalProtocol.id
        assert PERSONAL_PROTOCOL_ID != null

        PersonalProtocol updPersonalProtocol = personalProtocolService.get(PERSONAL_PROTOCOL_ID)
        assert updPersonalProtocol != null
        assert updPersonalProtocol instanceof PersonalProtocol
        updPersonalProtocol.number = '18-002'
        personalProtocolService.save(updPersonalProtocol)

        PersonalProtocol chkPersonalProtocol = personalProtocolService.get(PERSONAL_PROTOCOL_ID)
        assert chkPersonalProtocol != null
        assert chkPersonalProtocol instanceof PersonalProtocol
        assert chkPersonalProtocol.number == '18-002'
    }

    @Override
    void deleteTest() {
        def PERSONAL_PROTOCOL_ID
        def welder = welderService.save(getWelder())
        def journal = journalService.save(getJournal())
        def personalProtocol = getPersonalProtocol(welder, journal)
        personalProtocol = personalProtocolService.save(personalProtocol)
        PERSONAL_PROTOCOL_ID = personalProtocol.id
        assert PERSONAL_PROTOCOL_ID != null

        PersonalProtocol delPersonalProtocol = personalProtocolService.get(PERSONAL_PROTOCOL_ID)
        assert delPersonalProtocol != null
        assert delPersonalProtocol instanceof PersonalProtocol
        personalProtocolService.delete(delPersonalProtocol)

        PersonalProtocol chkPersonalProtocol = personalProtocolService.get(PERSONAL_PROTOCOL_ID)
        assert chkPersonalProtocol == null

    }

    @Override
    void getTest() {
        def PERSONAL_PROTOCOL_ID
        def welder = welderService.save(getWelder())
        def journal = journalService.save(getJournal())
        def personalProtocol = getPersonalProtocol(welder, journal)
        personalProtocol = personalProtocolService.save(personalProtocol)
        PERSONAL_PROTOCOL_ID = personalProtocol.id
        assert PERSONAL_PROTOCOL_ID != null

        PersonalProtocol chkPersonalProtocol = personalProtocolService.get(PERSONAL_PROTOCOL_ID)
        assert chkPersonalProtocol != null
        assert chkPersonalProtocol instanceof PersonalProtocol
    }

    @Override
    void getAllTest() {
        def PERSONAL_PROTOCOL_ID
        def welder = welderService.save(getWelder())
        def journal = journalService.save(getJournal())
        def personalProtocol = getPersonalProtocol(welder, journal)
        personalProtocol = personalProtocolService.save(personalProtocol)
        PERSONAL_PROTOCOL_ID = personalProtocol.id
        assert PERSONAL_PROTOCOL_ID != null

        def list = personalProtocolService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }

    @Override
    void countTest() {
        def PERSONAL_PROTOCOL_ID
        def welder = welderService.save(getWelder())
        def journal = journalService.save(getJournal())
        def personalProtocol = getPersonalProtocol(welder, journal)
        personalProtocol = personalProtocolService.save(personalProtocol)
        PERSONAL_PROTOCOL_ID = personalProtocol.id
        assert PERSONAL_PROTOCOL_ID != null

        def count = personalProtocolService.count()
        assert count != null
        assert count instanceof Long
        assert count == 1
    }
}
