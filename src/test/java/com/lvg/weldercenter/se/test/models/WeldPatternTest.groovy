package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Evaluation
import com.lvg.weldercenter.se.models.WeldDetailType
import com.lvg.weldercenter.se.models.WeldJoinType
import com.lvg.weldercenter.se.models.WeldPattern
import com.lvg.weldercenter.se.models.WeldPositionType
import org.junit.Test

class WeldPatternTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        def WELD_PATTERN_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            def welder = getWelder()
            def journal = getJournal()
            em.persist(welder)
            em.persist(journal)
            def pp = getPersonalProtocol(welder, journal)
            em.persist(pp)
            def weldPattern = getWeldPattern(pp)
            em.persist(weldPattern)
            WELD_PATTERN_ID = weldPattern.id
            return em
        }
        assert WELD_PATTERN_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            WeldPattern weldPattern1 = em.find(WeldPattern.class, WELD_PATTERN_ID)
            assert weldPattern1.id != null
            assert weldPattern1.mark == '01'
            assert weldPattern1.electrode == 'АНО-21'
            assert weldPattern1.diametr == Double.valueOf(89.0)
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
            return em
        }
    }

    @Override
    @Test
    void updateItemTest() {
        def WELD_PATTERN_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            def welder = getWelder()
            def journal = getJournal()
            em.persist(welder)
            em.persist(journal)
            def pp = getPersonalProtocol(welder, journal)
            em.persist(pp)
            def weldPattern = getWeldPattern(pp)
            em.persist(weldPattern)
            WELD_PATTERN_ID = weldPattern.id
            return em
        }
        assert WELD_PATTERN_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            def weldPatternUpd = em.find(WeldPattern.class, WELD_PATTERN_ID)
            weldPatternUpd.mark = '02'
            weldPatternUpd.weldDetail = WeldDetailType.P.value
            weldPatternUpd.diametr = 0.0
            weldPatternUpd.isHeatTreatment = true
            em.persist(weldPatternUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            def chkWeldPattern = em.find(WeldPattern.class, WELD_PATTERN_ID)
            assert chkWeldPattern.mark == '02'
            assert chkWeldPattern.diametr == 0.0
            assert chkWeldPattern.isHeatTreatment
            assert chkWeldPattern.weldDetail == WeldDetailType.P.value
            return em
        }

    }

    @Override
    @Test
    void deleteItemTest() {
        def WELD_PATTERN_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            def welder = getWelder()
            def journal = getJournal()
            em.persist(welder)
            em.persist(journal)
            def pp = getPersonalProtocol(welder, journal)
            em.persist(pp)
            def weldPattern = getWeldPattern(pp)
            em.persist(weldPattern)
            WELD_PATTERN_ID = weldPattern.id
            return em
        }
        assert WELD_PATTERN_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            def weldPatternUpd = em.find(WeldPattern.class, WELD_PATTERN_ID)
            em.remove(weldPatternUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            def chkWeldPattern = em.find(WeldPattern.class, WELD_PATTERN_ID)
            assert chkWeldPattern == null
            return em
        }

    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def welder = getWelder()
        welder.id = 100
        def journal = getJournal()
        journal.id = 100
        def pp = getPersonalProtocol(welder, journal)

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
        def weldPattern = getWeldPattern(pp)
        def wp = weldPattern

        assert wp.toString() == "01"
    }
}
