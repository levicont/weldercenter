package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Electrode
import org.junit.Test

class ElectrodeTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        def ELECTRODE_ID

        callInTransaction {
            def em = EMF.createEntityManager()
            Electrode electrode = getElectrode()
            em.persist(electrode)
            ELECTRODE_ID = electrode.id
            return em
        }
        assert ELECTRODE_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Electrode chkElectrode = em.find(Electrode.class, ELECTRODE_ID)
            assert chkElectrode.id != null
            assert chkElectrode.type == 'АНО-21'
            return em
        }



    }

    @Override
    @Test
    void updateItemTest() {
        def ELECTRODE_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Electrode electrode = getElectrode()
            em.persist(electrode)
            ELECTRODE_ID = electrode.id
            return em
        }
        assert ELECTRODE_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Electrode electrodeUpd = em.find(Electrode.class, ELECTRODE_ID)
            electrodeUpd.type = 'УОНИ'
            em.persist(electrodeUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Electrode chkElectrode = em.find(Electrode.class, ELECTRODE_ID)
            assert chkElectrode.type == 'УОНИ'
            return em
        }
    }

    @Override
    @Test
    void deleteItemTest() {
        def ELECTRODE_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Electrode electrode = getElectrode()
            em.persist(electrode)
            ELECTRODE_ID = electrode.id
            return em
        }
        assert ELECTRODE_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Electrode electrodeUpd = em.find(Electrode.class, ELECTRODE_ID)
            em.remove(electrodeUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Electrode chkElectrode = em.find(Electrode.class, ELECTRODE_ID)
            assert chkElectrode == null
            return em
        }
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
