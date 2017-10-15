package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Electrode
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction


class ElectrodeTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Electrode electrode = getElectrode()
        em.persist(electrode)
        def ELECTRODE_ID = electrode.id
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def electrode1 = em.find(Electrode.class, ELECTRODE_ID)
        tx.commit()



        assert electrode1.id != null
        assert electrode1.type == 'АНО-21'
    }

    @Override
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Electrode electrode = getElectrode()
        em.persist(electrode)
        tx.commit()

        assert electrode.id != null
        def ELECTRODE_ID = electrode.id
        tx.begin()
        em = EMF.createEntityManager()
        Electrode electrodeUpd = em.find(Electrode.class, ELECTRODE_ID)
        electrodeUpd.type = 'УОНИ'
        em.persist(electrodeUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Electrode chkElectrode = em.find(Electrode.class, ELECTRODE_ID)
        assert chkElectrode.type == 'УОНИ'
        tx.commit()
    }

    @Override
    @Test
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Electrode electrode = getElectrode()
        em.persist(electrode)
        tx.commit()

        assert electrode.id != null
        def ELECTRODE_ID = electrode.id

        tx.begin()
        em = EMF.createEntityManager()
        Electrode electrodeUpd = em.find(Electrode.class, ELECTRODE_ID)
        em.remove(electrodeUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Electrode chkElectrode = em.find(Electrode.class, ELECTRODE_ID)
        assert chkElectrode == null
        tx.commit()
    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def electrode1 = getElectrode()
        def electrode2 = getElectrode()

        assert electrode1 == electrode2

        electrode1.id = 100
        electrode2.id = 101

        assert electrode1 != electrode2

        def list = new HashSet<Electrode>()
        list.add(electrode1)
        electrode2.id = 100
        list.add(electrode2)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def electrode = getElectrode()
        electrode.type = 'АНО-21'
        assert electrode.toString() == 'АНО-21'
    }
}
