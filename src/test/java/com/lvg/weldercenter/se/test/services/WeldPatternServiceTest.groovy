package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.WeldPattern
import com.lvg.weldercenter.se.services.JournalService
import com.lvg.weldercenter.se.services.PersonalProtocolService
import com.lvg.weldercenter.se.services.WeldPatternService
import com.lvg.weldercenter.se.services.WelderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class WeldPatternServiceTest extends GenericServiceTest{

    @Autowired
    WeldPatternService weldPatternService
    @Autowired
    WelderService welderService
    @Autowired
    JournalService journalService
    @Autowired
    PersonalProtocolService personalProtocolService

    @Override
    void saveTest() {
        def WELD_PATTERN_ID
        def welder = welderService.save(getWelder())
        def journal = journalService.save(getJournal())
        def pp = personalProtocolService.save(getPersonalProtocol(welder, journal))
        def weldPattern = getWeldPattern(pp)
        weldPattern = weldPatternService.save(weldPattern)
        WELD_PATTERN_ID = weldPattern.id
        assert WELD_PATTERN_ID != null

        WeldPattern updWeldPattern = weldPatternService.get(WELD_PATTERN_ID)
        assert updWeldPattern != null
        assert updWeldPattern instanceof WeldPattern
        updWeldPattern.mark = '01'
        weldPatternService.save(updWeldPattern)

        WeldPattern chkWeldPattern = weldPatternService.get(WELD_PATTERN_ID)
        assert chkWeldPattern != null
        assert chkWeldPattern instanceof WeldPattern
        assert chkWeldPattern.mark == '01'
    }

    @Override
    void deleteTest() {
        def WELD_PATTERN_ID
        def welder = welderService.save(getWelder())
        def journal = journalService.save(getJournal())
        def pp = personalProtocolService.save(getPersonalProtocol(welder, journal))
        def weldPattern = getWeldPattern(pp)
        weldPattern = weldPatternService.save(weldPattern)
        WELD_PATTERN_ID = weldPattern.id
        assert WELD_PATTERN_ID != null

        WeldPattern delWeldPattern = weldPatternService.get(WELD_PATTERN_ID)
        assert delWeldPattern != null
        assert delWeldPattern instanceof WeldPattern
        weldPatternService.delete(delWeldPattern)

        WeldPattern chkWeldPattern = weldPatternService.get(WELD_PATTERN_ID)
        assert chkWeldPattern == null
    }

    @Override
    void getTest() {
        def WELD_PATTERN_ID
        def welder = welderService.save(getWelder())
        def journal = journalService.save(getJournal())
        def pp = personalProtocolService.save(getPersonalProtocol(welder, journal))
        def weldPattern = getWeldPattern(pp)
        weldPattern = weldPatternService.save(weldPattern)
        WELD_PATTERN_ID = weldPattern.id
        assert WELD_PATTERN_ID != null

        WeldPattern updWeldPattern = weldPatternService.get(WELD_PATTERN_ID)
        assert updWeldPattern != null
        assert updWeldPattern instanceof WeldPattern
    }

    @Override
    void getAllTest() {
        def WELD_PATTERN_ID
        def welder = welderService.save(getWelder())
        def journal = journalService.save(getJournal())
        def pp = personalProtocolService.save(getPersonalProtocol(welder, journal))
        def weldPattern = getWeldPattern(pp)
        weldPattern = weldPatternService.save(weldPattern)
        WELD_PATTERN_ID = weldPattern.id
        assert WELD_PATTERN_ID != null

        def list = weldPatternService.getAll()
        assert list != null
        assert list instanceof List
        assert list.size() == 1
    }
}
