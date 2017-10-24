package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Qualification
import org.junit.Test


class QualificationTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        def QUALIFICATION_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Qualification qualification = getQualification()
            em.persist(qualification)
            QUALIFICATION_ID = qualification.id
            return em
        }
        assert QUALIFICATION_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Qualification chkQualification = em.find(Qualification.class, QUALIFICATION_ID)
            assert chkQualification.id != null
            assert chkQualification.type == 'электросварщик'
            return em
        }
    }

    @Override
    @Test
    void updateItemTest() {
        def QUALIFICATION_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Qualification qualification = getQualification()
            em.persist(qualification)
            QUALIFICATION_ID = qualification.id
            return em
        }
        assert QUALIFICATION_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Qualification qualificationUpd = em.find(Qualification.class, QUALIFICATION_ID)
            qualificationUpd.type = 'газосварщик'
            em.persist(qualificationUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Qualification chkQualification = em.find(Qualification.class, QUALIFICATION_ID)
            assert chkQualification.type == 'газосварщик'
            return em
        }
    }

    @Override
    @Test
    void deleteItemTest() {
        def QUALIFICATION_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Qualification qualification = getQualification()
            em.persist(qualification)
            QUALIFICATION_ID = qualification.id
            return em
        }
        assert QUALIFICATION_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Qualification qualificationUpd = em.find(Qualification.class, QUALIFICATION_ID)
            em.remove(qualificationUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Qualification chkQualification = em.find(Qualification.class, QUALIFICATION_ID)
            assert chkQualification == null
            return em
        }
    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def qualification1 = getQualification()
        def qualification2 = getQualification()

        assert qualification1 == qualification2

        qualification1.id = 100
        qualification2.id = 101

        assert qualification1 != qualification2

        def list = new HashSet<Qualification>()
        list.add(qualification1)
        qualification2.id = 100
        list.add(qualification2)

        assert list.size() == 1
    }

    @Override
    @Test
    void toStringTest() {
        def qualification = getQualification()
        qualification.type = 'газосварщик'
        assert qualification.toString() == 'газосварщик'
    }
}
