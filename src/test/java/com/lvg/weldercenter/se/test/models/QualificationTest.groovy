package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Qualification
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction

/**
 * Created by Victor on 05.10.2017.
 */
class QualificationTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Qualification qualification = getQualification()
        em.persist(qualification)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def list = em.createQuery('select q from Qualification q').getResultList()
        tx.commit()

        assert list.size() == 1
        qualification = list.get(0)


        assert qualification.id != null
        assert qualification.type == 'электросварщик'
    }

    @Override
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Qualification qualification = getQualification()
        em.persist(qualification)
        tx.commit()

        assert qualification.id != null
        def QUALIFICATION_ID = qualification.id
        tx.begin()
        em = EMF.createEntityManager()
        Qualification qualificationUpd = em.find(Qualification.class, QUALIFICATION_ID)
        qualificationUpd.type = 'газосварщик'
        em.persist(qualificationUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Qualification chkQualification = em.find(Qualification.class, QUALIFICATION_ID)
        assert chkQualification.type == 'газосварщик'
        tx.commit()
    }

    @Override
    @Test
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Qualification qualification = getQualification()
        em.persist(qualification)
        tx.commit()

        assert qualification.id != null
        def QUALIFICATION_ID = qualification.id

        tx.begin()
        em = EMF.createEntityManager()
        Qualification qualificationUpd = em.find(Qualification.class, QUALIFICATION_ID)
        em.remove(qualificationUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Qualification chkQualification = em.find(Qualification.class, QUALIFICATION_ID)
        assert chkQualification == null
        tx.commit()

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
