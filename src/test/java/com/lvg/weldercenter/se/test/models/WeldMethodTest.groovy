package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.WeldMethod
import com.lvg.weldercenter.se.models.WeldMethodType
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction

/**
 * Created by Victor on 06.10.2017.
 */
class WeldMethodTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        WeldMethod weldMethod = getWeldMethod()
        em.persist(weldMethod)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def list = em.createQuery('select wm from WeldMethod wm').getResultList()
        tx.commit()

        assert list.size() == 1
        weldMethod = list.get(0)


        assert weldMethod.id != null
        assert weldMethod.name == 'РДЭ'
        assert weldMethod.code == '111'
    }

    @Override
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        WeldMethod weldMethod = getWeldMethod()
        em.persist(weldMethod)
        tx.commit()

        assert weldMethod.id != null
        def WELD_METHOD_ID = weldMethod.id
        tx.begin()
        em = EMF.createEntityManager()
        WeldMethod weldMethodUpd = em.find(WeldMethod.class, WELD_METHOD_ID)
        weldMethodUpd.name = 'ВИГ'
        em.persist(weldMethodUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        WeldMethod chkWeldMethod = em.find(WeldMethod.class, WELD_METHOD_ID)
        assert chkWeldMethod.name == 'ВИГ'
        tx.commit()
    }

    @Override
    @Test
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        WeldMethod weldMethod = getWeldMethod()
        em.persist(weldMethod)
        tx.commit()

        assert weldMethod.id != null
        def WELD_METHOD_ID = weldMethod.id

        tx.begin()
        em = EMF.createEntityManager()
        WeldMethod weldMethodUpd = em.find(WeldMethod.class, WELD_METHOD_ID)
        em.remove(weldMethodUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        WeldMethod chkWeldMethod = em.find(WeldMethod.class, WELD_METHOD_ID)
        assert chkWeldMethod == null
        tx.commit()

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
