package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.SteelGroup
import com.lvg.weldercenter.se.models.SteelType
import org.junit.Test


class SteelTypeTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        def STEEL_TYPE_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            SteelType steelType = getSteelType()
            em.persist(steelType)
            STEEL_TYPE_ID = steelType.id
            return em
        }
        assert STEEL_TYPE_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            SteelType steelType1 = em.find(SteelType.class, STEEL_TYPE_ID)
            assert steelType1.id != null
            assert steelType1.type == 'сталь 20'
            return em
        }
    }

    @Override
    @Test
    void updateItemTest() {
        def STEEL_TYPE_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            SteelType steelType = getSteelType()
            em.persist(steelType)
            STEEL_TYPE_ID = steelType.id
            return em
        }
        assert STEEL_TYPE_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            SteelType steelTypeUpd = em.find(SteelType.class, STEEL_TYPE_ID)
            steelTypeUpd.type = 'сталь 40'
            steelTypeUpd.steelGroup = SteelGroup.W02
            em.persist(steelTypeUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            SteelType chkSteelType = em.find(SteelType.class, STEEL_TYPE_ID)
            assert chkSteelType.type == 'сталь 40'
            assert chkSteelType.steelGroup == SteelGroup.W02
            return em
        }
    }

    @Override
    @Test
    void deleteItemTest() {
        def STEEL_TYPE_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            SteelType steelType = getSteelType()
            em.persist(steelType)
            STEEL_TYPE_ID = steelType.id
            return em
        }
        assert STEEL_TYPE_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            SteelType steelTypeUpd = em.find(SteelType.class, STEEL_TYPE_ID)
            em.remove(steelTypeUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            SteelType chkSteelType = em.find(SteelType.class, STEEL_TYPE_ID)
            assert chkSteelType == null
            return em
        }
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
