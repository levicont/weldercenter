package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Education
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction

/**
 * Created by Victor on 05.10.2017.
 */
class EducationTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Education education = getEducation()
        em.persist(education)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def list = em.createQuery('select e from Education e').getResultList()
        tx.commit()

        assert list.size() == 1
        education = list.get(0)


        assert education.id != null
        assert education.education == 'среднее-специальное'

    }

    @Override
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Education education = getEducation()
        em.persist(education)
        tx.commit()

        assert education.id != null
        def EDUCATION_ID = education.id
        tx.begin()
        em = EMF.createEntityManager()
        Education educationUpd = em.find(Education.class, EDUCATION_ID)
        educationUpd.education = 'hight'
        em.persist(educationUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Education chkEducation = em.find(Education.class, EDUCATION_ID)
        assert chkEducation.education == 'hight'
        tx.commit()
    }

    @Override
    @Test
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Education education = getEducation()
        em.persist(education)
        tx.commit()

        assert education.id != null
        def EDUCATION_ID = education.id

        tx.begin()
        em = EMF.createEntityManager()
        Education educationUpd = em.find(Education.class, EDUCATION_ID)
        em.remove(educationUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Education chkEducation = em.find(Education.class, EDUCATION_ID)
        assert chkEducation == null
        tx.commit()


    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def org1 = getEducation()
        def org2 = getEducation()

        assert org1 == org2

        org1.id = 100
        org2.id = 101

        assert org1 != org2

        def list = new HashSet<Education>()
        list.add(org1)
        org2.id = 100
        list.add(org2)

        assert list.size() == 1

    }

    @Override
    @Test
    void toStringTest() {
        def education = getEducation()
        education.education = 'высшее'
        assert education.toString() == 'высшее'
    }
}
