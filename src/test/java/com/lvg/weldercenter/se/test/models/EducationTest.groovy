package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Education
import org.junit.Test

class EducationTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        def EDUCATION_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Education education = getEducation()
            em.persist(education)
            EDUCATION_ID = education.id
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Education chkEducation = em.find(Education.class, EDUCATION_ID)
            assert chkEducation.id != null
            assert chkEducation.education == 'среднее-специальное'
            return em
        }
    }

    @Override
    @Test
    void updateItemTest() {
        def EDUCATION_ID

        callInTransaction {
            def em = EMF.createEntityManager()
            Education education = getEducation()
            em.persist(education)
            EDUCATION_ID = education.id
            return em
        }
        assert EDUCATION_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Education educationUpd = em.find(Education.class, EDUCATION_ID)
            educationUpd.education = 'hight'
            em.persist(educationUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Education chkEducation = em.find(Education.class, EDUCATION_ID)
            assert chkEducation.education == 'hight'
            return em
        }
    }

    @Override
    @Test
    void deleteItemTest() {
        def EDUCATION_ID

        callInTransaction {
            def em = EMF.createEntityManager()
            Education education = getEducation()
            em.persist(education)
            EDUCATION_ID = education.id
            return em
        }
        assert EDUCATION_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Education educationUpd = em.find(Education.class, EDUCATION_ID)
            em.remove(educationUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Education chkEducation = em.find(Education.class, EDUCATION_ID)
            assert chkEducation == null
            return em
        }
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
