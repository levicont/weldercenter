package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Evaluation
import com.lvg.weldercenter.se.models.WeldDetailType
import com.lvg.weldercenter.se.models.WeldJoinType
import com.lvg.weldercenter.se.models.WeldPattern
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction

class WeldPatternTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        def weldPattern = getWeldPattern()
        em.persist(weldPattern)
        def WELD_PATTERN_ID = weldPattern.id
        assert WELD_PATTERN_ID != null
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        WeldPattern weldPattern1 = em.find(WeldPattern.class, WELD_PATTERN_ID)

        tx.commit()
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
    }

    @Override
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        def weldPattern = getWeldPattern()
        em.persist(weldPattern)
        tx.commit()

        assert weldPattern.id != null
        def WELD_PATTERN_ID = weldPattern.id
        tx.begin()
        em = EMF.createEntityManager()
        def weldPatternUpd = em.find(WeldPattern.class, WELD_PATTERN_ID)
        weldPatternUpd.mark = '02'
        weldPatternUpd.weldDetail = WeldDetailType.P.value
        weldPatternUpd.diametr = 0.0
        weldPatternUpd.isHeatTreatment = true
        em.persist(weldPatternUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def chkWeldPattern = em.find(WeldPattern.class, WELD_PATTERN_ID)
        assert chkWeldPattern.mark == '02'
        assert chkWeldPattern.diametr == 0.0
        assert chkWeldPattern.isHeatTreatment == true
        assert chkWeldPattern.weldDetail == WeldDetailType.P.value
        tx.commit()

    }

    @Override
    @Test
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        def wp = getWeldPattern()
        em.persist(wp)
        tx.commit()

        assert wp.id != null
        def WELD_PATTERN_ID = wp.id

        tx.begin()
        em = EMF.createEntityManager()
        def weldPatternUpd = em.find(WeldPattern.class, WELD_PATTERN_ID)
        em.remove(weldPatternUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def chkWeldPattern = em.find(WeldPattern.class, WELD_PATTERN_ID)
        assert chkWeldPattern == null
        tx.commit()

    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def wp1 = getWeldPattern()
        def wp2 = getWeldPattern()

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
        def wp = getWeldPattern()

        assert wp.toString() == "01"
    }
}
