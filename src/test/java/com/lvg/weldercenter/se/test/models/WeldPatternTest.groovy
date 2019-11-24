package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Evaluation
import com.lvg.weldercenter.se.models.WeldDetailType
import com.lvg.weldercenter.se.models.WeldJoinType
import com.lvg.weldercenter.se.models.WeldPattern
import com.lvg.weldercenter.se.models.WeldPositionType
import com.lvg.weldercenter.se.services.JournalService
import com.lvg.weldercenter.se.services.PersonalProtocolService
import com.lvg.weldercenter.se.services.WeldPatternService
import com.lvg.weldercenter.se.services.WelderService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class WeldPatternTest extends GenericModelTest {

    @Autowired
    WelderService welderService
    @Autowired
    JournalService journalService
    @Autowired
    PersonalProtocolService personalProtocolService
    @Autowired
    WeldPatternService weldPatternService

    @Override
    @Test
    void insertItemTest() {
        def WELD_PATTERN_ID

        def welder = getWelder()
        def journal = getJournal()
        welderService.save(welder)
        journalService.save(journal)
        def pp = getPersonalProtocol(welder, journal)
        personalProtocolService.save(pp)
        def weldPattern = getWeldPattern(pp)
        weldPatternService.save(weldPattern)
        WELD_PATTERN_ID = weldPattern.id

        assert WELD_PATTERN_ID != null


        WeldPattern weldPattern1 = weldPatternService.get(WELD_PATTERN_ID)
        assert weldPattern1.id != null
        assert weldPattern1.mark == '01'
        assert weldPattern1.electrode == 'АНО-21'
        assert weldPattern1.diameter == Double.valueOf(89.0)
        assert weldPattern1.thickness == Double.valueOf(3.0)
        assert !weldPattern1.isHeating
        assert !weldPattern1.isHeatTreatment
        assert weldPattern1.weldGas == 'Аргон'
        assert weldPattern1.weldWire == 'св08Г2С'
        assert weldPattern1.steelType == 'сталь 20'
        assert weldPattern1.radiationTest.defects == 'ДНО'
        assert weldPattern1.radiationTest.evaluation == Evaluation.E
        assert weldPattern1.visualTest.defects == 'ДНО'
        assert weldPattern1.visualTest.evaluation == Evaluation.E
        assert weldPattern1.mechanicalTest.clearance == 9.0D
        assert weldPattern1.visualTest.evaluation == Evaluation.E
        assert weldPattern1.weldDetail == WeldDetailType.P.value
        assert weldPattern1.weldJoins.contains(WeldJoinType.GG.value) && weldPattern1.weldJoins.contains(WeldJoinType.BS.value)
        assert weldPattern1.weldPositions.contains(WeldPositionType.PA.value) &&
                weldPattern1.weldPositions.contains(WeldPositionType.PB.value)

    }

    @Override
    @Test
    void updateItemTest() {
        def WELD_PATTERN_ID

        def welder = getWelder()
        def journal = getJournal()
        welderService.save(welder)
        journalService.save(journal)
        def pp = getPersonalProtocol(welder, journal)
        personalProtocolService.save(pp)
        def weldPattern = getWeldPattern(pp)
        weldPatternService.save(weldPattern)
        WELD_PATTERN_ID = weldPattern.id

        assert WELD_PATTERN_ID != null


        def weldPatternUpd = weldPatternService.get(WELD_PATTERN_ID)
        weldPatternUpd.mark = '02'
        weldPatternUpd.weldDetail = WeldDetailType.P.value
        weldPatternUpd.diameter = 0.0
        weldPatternUpd.isHeatTreatment = true
        weldPatternService.save(weldPatternUpd)



        def chkWeldPattern = weldPatternService.get(WELD_PATTERN_ID)
        assert chkWeldPattern.mark == '02'
        assert chkWeldPattern.diameter == 0.0D
        assert chkWeldPattern.isHeatTreatment
        assert chkWeldPattern.weldDetail == WeldDetailType.P.value


    }

    @Override
    @Test
    void deleteItemTest() {
        def WELD_PATTERN_ID

        def welder = getWelder()
        def journal = getJournal()
        welderService.save(welder)
        journalService.save(journal)
        def pp = getPersonalProtocol(welder, journal)
        personalProtocolService.save(pp)
        def weldPattern = getWeldPattern(pp)
        weldPatternService.save(weldPattern)
        WELD_PATTERN_ID = weldPattern.id

        assert WELD_PATTERN_ID != null

        def weldPatternUpd = weldPatternService.get(WELD_PATTERN_ID)
        weldPatternService.delete(weldPatternUpd)

        def chkWeldPattern = weldPatternService.get(WELD_PATTERN_ID)
        assert chkWeldPattern == null
    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def welder = getWelder()
        welder.id = 100
        def journal = getJournal()
        journal.id = 100
        def pp = getPersonalProtocol(welder, journal)
        pp.id = 100
        def wp1 = getWeldPattern(pp)
        def wp2 = getWeldPattern(pp)

        assert wp1 == wp2

        wp1.id = 100
        wp2.id = 101

        assert wp1 != wp2

        def list = new HashSet<WeldPattern>()
        list.add(wp1)
        wp2.id = 100
        list.add(wp2)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def welder = getWelder()
        welder.id = 100
        def journal = getJournal()
        journal.id = 100
        def pp = getPersonalProtocol(welder, journal)
        pp.id = 100
        def weldPattern = getWeldPattern(pp)
        def wp = weldPattern

        assert wp.toString() == "01"
    }
}
