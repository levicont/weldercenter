package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.CommissionCertification
import com.lvg.weldercenter.se.models.Teacher
import org.junit.Test

class CommissionCertificationTest extends GenericModelTest {

    @Override
    @Test
    void insertItemTest() {
        def COMMISSION_ID
        callInTransaction{
            def em = EMF.createEntityManager()
            def teachers = new ArrayList<Teacher>()
            (1..4).each {teachers << getTeacher()}
            teachers.each{em.persist(it)}
            CommissionCertification commission = getCommissionCertification(teachers)
            em.persist(commission)
            COMMISSION_ID = commission.id
            return em
        }

        assert COMMISSION_ID != null
        def chkCommission = null
        callInTransaction {
            def em = EMF.createEntityManager()
            chkCommission = em.find(CommissionCertification.class, COMMISSION_ID)
            assert chkCommission != null
            assert chkCommission.id == COMMISSION_ID
            assert chkCommission.safetySpecialist.surname == getTeacher().surname
            assert chkCommission.ndtSpecialist.name == getTeacher().name
            assert chkCommission.weldSpecialist.secondName == getTeacher().secondName
            assert chkCommission.head.toString() == getTeacher().toString()
            return em
        }
    }

    @Override
    @Test
    void updateItemTest() {
        def COMMISSION_ID

        callInTransaction {
            def em = EMF.createEntityManager()
            def teachers = new ArrayList<Teacher>()
            (1..4).each {teachers << getTeacher()}
            teachers.each{em.persist(it)}
            CommissionCertification commission = getCommissionCertification(teachers)
            em.persist(commission)
            COMMISSION_ID = commission.id
            return em
        }

        assert COMMISSION_ID != null

        def updCommission
        callInTransaction {
            def em = EMF.createEntityManager()
            updCommission = em.find(CommissionCertification.class, COMMISSION_ID)
            def teacher = getTeachers().find { it.surname == 'Новировский' }
            assert teacher != null
            updCommission.head = teacher
            em.persist(teacher)
            em.persist(updCommission)
            return em
        }

        def chkCommission
        callInTransaction {
            def em = EMF.createEntityManager()
            chkCommission = em.find(CommissionCertification.class, COMMISSION_ID)
            assert chkCommission != null
            assert chkCommission.head != null
            assert chkCommission.head.surname == 'Новировский'
            return em
        }
    }

    @Override
    @Test
    void deleteItemTest() {
        def COMMISSION_ID
        callInTransaction{
            def em = EMF.createEntityManager()
            def teachers = new ArrayList<Teacher>()
            (1..4).each {teachers << getTeacher()}
            teachers.each{em.persist(it)}
            CommissionCertification commission = getCommissionCertification(teachers)
            em.persist(commission)
            COMMISSION_ID = commission.id
            return em
        }
        assert COMMISSION_ID != null

        def delCommission = null
        callInTransaction{
            def em = EMF.createEntityManager()
            delCommission = em.find(CommissionCertification.class, COMMISSION_ID)
            em.remove(delCommission)
            return em
        }
        def chkCommission = null
        callInTransaction{
            def em = EMF.createEntityManager()
            chkCommission = em.find(CommissionCertification.class, COMMISSION_ID)
            return em
        }
        assert chkCommission == null


    }

    @Override
    void equalsHashCodeTest() {

    }

    @Override
    void toStringTest() {

    }
}
