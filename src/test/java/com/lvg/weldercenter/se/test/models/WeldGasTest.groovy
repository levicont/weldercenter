package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.WeldGas
import org.junit.Test


class WeldGasTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        def WELD_GAS_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            WeldGas weldGas = getWeldGas()
            em.persist(weldGas)
            WELD_GAS_ID = weldGas.id
            return em
        }
        assert WELD_GAS_ID != null
        callInTransaction {
            def em = EMF.createEntityManager()
            def weldGas1 = em.find(WeldGas.class, WELD_GAS_ID)
            assert weldGas1.id != null
            assert weldGas1.type == 'Аргон'
            return em
        }
    }

    @Override
    @Test
    void updateItemTest() {
        def WELD_GAS_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            WeldGas weldGas = getWeldGas()
            em.persist(weldGas)
            WELD_GAS_ID = weldGas.id
            return em
        }
        assert WELD_GAS_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            WeldGas weldGasUpd = em.find(WeldGas.class, WELD_GAS_ID)
            weldGasUpd.type = 'CO2'
            em.persist(weldGasUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            WeldGas chkWeldGas = em.find(WeldGas.class, WELD_GAS_ID)
            assert chkWeldGas.type == 'CO2'
            return em
        }
    }

    @Override
    @Test
    void deleteItemTest() {
        def WELD_GAS_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            WeldGas weldGas = getWeldGas()
            em.persist(weldGas)
            WELD_GAS_ID = weldGas.id
            return em
        }
        assert WELD_GAS_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            WeldGas weldGasUpd = em.find(WeldGas.class, WELD_GAS_ID)
            em.remove(weldGasUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            WeldGas chkWeldGas = em.find(WeldGas.class, WELD_GAS_ID)
            assert chkWeldGas == null
            return em
        }
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
