package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.SteelGroup
import com.lvg.weldercenter.se.models.SteelType
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction


class SteelTypeTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        SteelType steelType = getSteelType()
        em.persist(steelType)
        def STEEL_TYPE_ID = steelType.id
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def steelType1 = em.find(SteelType.class, STEEL_TYPE_ID)
        tx.commit()

        assert steelType1.id != null
        assert steelType1.type == 'сталь 20'
    }

    @Override
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        SteelType steelType = getSteelType()
        em.persist(steelType)
        tx.commit()

        assert steelType.id != null
        def STEEL_TYPE_ID = steelType.id
        tx.begin()
        em = EMF.createEntityManager()
        SteelType steelTypeUpd = em.find(SteelType.class, STEEL_TYPE_ID)
        steelTypeUpd.type = 'сталь 40'
        steelTypeUpd.steelGroup = SteelGroup.W02
        em.persist(steelTypeUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        SteelType chkSteelType = em.find(SteelType.class, STEEL_TYPE_ID)
        tx.commit()
        assert chkSteelType.type == 'сталь 40'
        assert chkSteelType.steelGroup == SteelGroup.W02

    }

    @Override
    @Test
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        SteelType steelType = getSteelType()
        em.persist(steelType)
        tx.commit()

        assert steelType.id != null
        def STEEL_TYPE_ID = steelType.id

        tx.begin()
        em = EMF.createEntityManager()
        SteelType steelTypeUpd = em.find(SteelType.class, STEEL_TYPE_ID)
        em.remove(steelTypeUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        SteelType chkSteelType = em.find(SteelType.class, STEEL_TYPE_ID)
        assert chkSteelType == null
        tx.commit()
    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def steelType1 = getSteelType()
        def steelType2 = getSteelType()

        assert steelType1 == steelType2

        steelType1.id = 100
        steelType2.id = 101

        assert steelType1 != steelType2

        def list = new HashSet<SteelType>()
        list.add(steelType1)
        steelType2.id = 100
        list.add(steelType2)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def steelType = getSteelType()
        steelType.type = 'сталь 09Г2С'
        assert steelType.toString() == 'сталь 09Г2С'
    }
}
