package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.WeldGas
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction


class WeldGasTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        WeldGas weldGas = getWeldGas()
        em.persist(weldGas)
        def WELD_GAS_ID = weldGas.id
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def weldGas1 = em.find(WeldGas.class, WELD_GAS_ID)
        tx.commit()



        assert weldGas1.id != null
        assert weldGas1.type == 'Аргон'
    }

    @Override
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        WeldGas weldGas = getWeldGas()
        em.persist(weldGas)
        tx.commit()

        assert weldGas.id != null
        def WELD_GAS_ID = weldGas.id
        tx.begin()
        em = EMF.createEntityManager()
        WeldGas weldGasUpd = em.find(WeldGas.class, WELD_GAS_ID)
        weldGasUpd.type = 'CO2'
        em.persist(weldGasUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        WeldGas chkWeldGas = em.find(WeldGas.class, WELD_GAS_ID)
        assert chkWeldGas.type == 'CO2'
        tx.commit()
    }

    @Override
    @Test
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        WeldGas weldGas = getWeldGas()
        em.persist(weldGas)
        tx.commit()

        assert weldGas.id != null
        def WELD_GAS_ID = weldGas.id

        tx.begin()
        em = EMF.createEntityManager()
        WeldGas weldGasUpd = em.find(WeldGas.class, WELD_GAS_ID)
        em.remove(weldGasUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        WeldGas chkWeldGas = em.find(WeldGas.class, WELD_GAS_ID)
        assert chkWeldGas == null
        tx.commit()
    }

    @Override
    void equalsHashCodeTest() {
        def weldGas1 = getWeldGas()
        def weldGas2 = getWeldGas()

        assert weldGas1 == weldGas2

        weldGas1.id = 100
        weldGas2.id = 101

        assert weldGas1 != weldGas2

        def list = new HashSet<WeldGas>()
        list.add(weldGas1)
        weldGas2.id = 100
        list.add(weldGas2)

        assert list.size() == 1
    }

    @Override
    void toStringTest() {
        def weldGas = getWeldGas()
        weldGas.type = 'Аргон'
        assert weldGas.toString() == 'Аргон'
    }
}
