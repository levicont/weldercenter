package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.WeldMethod
import com.lvg.weldercenter.se.models.WeldMethodType
import org.junit.Test

class WeldMethodTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        def WELD_METHOD_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            WeldMethod weldMethod = getWeldMethod()
            em.persist(weldMethod)
            WELD_METHOD_ID = weldMethod.id
            return em
        }
        assert WELD_METHOD_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            def chkWeldMethod = em.find(WeldMethod.class, WELD_METHOD_ID)
            assert chkWeldMethod != null
            assert chkWeldMethod.id != null
            assert chkWeldMethod.name == 'РДЭ'
            assert chkWeldMethod.code == '111'
            return em
        }
    }

    @Override
    @Test
    void updateItemTest() {
        def WELD_METHOD_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            WeldMethod weldMethod = getWeldMethod()
            em.persist(weldMethod)
            WELD_METHOD_ID = weldMethod.id
            return em
        }
        assert WELD_METHOD_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            WeldMethod weldMethodUpd = em.find(WeldMethod.class, WELD_METHOD_ID)
            weldMethodUpd.name = 'ВИГ'
            em.persist(weldMethodUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            WeldMethod chkWeldMethod = em.find(WeldMethod.class, WELD_METHOD_ID)
            assert chkWeldMethod.name == 'ВИГ'
            return em
        }
    }

    @Override
    @Test
    void deleteItemTest() {
        def WELD_METHOD_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            WeldMethod weldMethod = getWeldMethod()
            em.persist(weldMethod)
            WELD_METHOD_ID = weldMethod.id
            return em
        }
        assert WELD_METHOD_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            WeldMethod weldMethodUpd = em.find(WeldMethod.class, WELD_METHOD_ID)
            em.remove(weldMethodUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            WeldMethod chkWeldMethod = em.find(WeldMethod.class, WELD_METHOD_ID)
            assert chkWeldMethod == null
            return em
        }
    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def weldMethod1 = getWeldMethod()
        def weldMethod2 = getWeldMethod()

        assert weldMethod1 == weldMethod2

        weldMethod1.id = 100
        weldMethod2.id = 101

        assert weldMethod1 != weldMethod2

        def list = new HashSet<WeldMethod>()
        list.add(weldMethod1)
        weldMethod2.id = 100
        list.add(weldMethod2)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def wm = getWeldMethod()
        def wmt = WeldMethodType.MMA
        assert wm.toString() == 'РДЭ (111)'
        assert "$wmt" == "$wm"

    }
}
