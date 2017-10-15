package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.WeldWire
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction


class WeldWireTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        WeldWire weldWire = getWeldWire()
        em.persist(weldWire)
        def WELD_WIRE_ID = weldWire.id
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def weldWire1 = em.find(WeldWire.class, WELD_WIRE_ID)
        tx.commit()



        assert weldWire1.id != null
        assert weldWire1.type == 'св08Г2С'
    }

    @Override
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        WeldWire weldWire = getWeldWire()
        em.persist(weldWire)
        tx.commit()

        assert weldWire.id != null
        def WELD_WIRE_ID = weldWire.id
        tx.begin()
        em = EMF.createEntityManager()
        WeldWire weldWire1Upd = em.find(WeldWire.class, WELD_WIRE_ID)
        weldWire1Upd.type = 'св08Г'
        em.persist(weldWire1Upd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        WeldWire chkWeldWire = em.find(WeldWire.class, WELD_WIRE_ID)
        assert chkWeldWire.type == 'св08Г'
        tx.commit()
    }

    @Override
    @Test
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        WeldWire weldWire = getWeldWire()
        em.persist(weldWire)
        tx.commit()

        assert weldWire.id != null
        def WELD_WIRE_ID = weldWire.id

        tx.begin()
        em = EMF.createEntityManager()
        WeldWire weldWireUpd = em.find(WeldWire.class, WELD_WIRE_ID)
        em.remove(weldWireUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        WeldWire chkWeldWire = em.find(WeldWire.class, WELD_WIRE_ID)
        assert chkWeldWire == null
        tx.commit()
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
