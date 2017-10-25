package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.WeldWire
import org.junit.Test

class WeldWireTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        def WELD_WIRE_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            WeldWire weldWire = getWeldWire()
            em.persist(weldWire)
            WELD_WIRE_ID = weldWire.id
            return em
        }
        assert WELD_WIRE_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            def weldWire1 = em.find(WeldWire.class, WELD_WIRE_ID)
            assert weldWire1.id != null
            assert weldWire1.type == 'св08Г2С'
            return em
        }
    }

    @Override
    @Test
    void updateItemTest() {
        def WELD_WIRE_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            WeldWire weldWire = getWeldWire()
            em.persist(weldWire)
            WELD_WIRE_ID = weldWire.id
            return em
        }
        assert WELD_WIRE_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            WeldWire weldWire1Upd = em.find(WeldWire.class, WELD_WIRE_ID)
            weldWire1Upd.type = 'св08Г'
            em.persist(weldWire1Upd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            WeldWire chkWeldWire = em.find(WeldWire.class, WELD_WIRE_ID)
            assert chkWeldWire.type == 'св08Г'
            return em
        }
    }

    @Override
    @Test
    void deleteItemTest() {
        def WELD_WIRE_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            WeldWire weldWire = getWeldWire()
            em.persist(weldWire)
            WELD_WIRE_ID = weldWire.id
            return em
        }
        assert WELD_WIRE_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            WeldWire weldWireUpd = em.find(WeldWire.class, WELD_WIRE_ID)
            em.remove(weldWireUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            WeldWire chkWeldWire = em.find(WeldWire.class, WELD_WIRE_ID)
            assert chkWeldWire == null
            return em
        }
    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def weldWire2 = getWeldWire()
        def weldWire1 = getWeldWire()

        assert weldWire2 == weldWire1

        weldWire2.id = 100
        weldWire1.id = 101

        assert weldWire2 != weldWire1

        def list = new HashSet<WeldWire>()
        list.add(weldWire2)
        weldWire1.id = 100
        list.add(weldWire1)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def weldWire = getWeldWire()
        weldWire.type = 'св08Г2С'
        assert weldWire.toString() == 'св08Г2С'
    }
}
