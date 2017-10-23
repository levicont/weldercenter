package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.CommissionCertification
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction

class CommissionCertificationTest extends GenericModelTest {

    @Override
    @Test
    void insertItemTest() {

        UserTransaction tx = TMS.getUserTransaction()
        def COMMISSION_ID
        EntityManager em
        def insert = {
            em = EMF.createEntityManager()
            CommissionCertification commission = getCommissionCertification()
            em.persist(commission.head)
            em.persist(commission.weldSpecialist)
            em.persist(commission.ndtSpecialist)
            em.persist(commission.safetySpecialist)
            em.persist(commission)
            COMMISSION_ID = commission.id
            return em
        }

        callInTransaction(tx, insert)

        assert COMMISSION_ID != null
        def chkCommission = null
        def find = {
            em = EMF.createEntityManager()
            chkCommission = em.find(CommissionCertification.class, COMMISSION_ID)
            return em
        }

        callInTransaction(tx, find)
        assert chkCommission != null
        assert chkCommission.id == COMMISSION_ID
        assert chkCommission.safetySpecialist.surname == getTeacher().surname
        assert chkCommission.ndtSpecialist.name == getTeacher().name
        assert chkCommission.weldSpecialist.secondName == getTeacher().secondName
        assert chkCommission.head.toString() == getTeacher().toString()

    }

    @Override
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        EntityManager em = null
        def COMMISSION_ID

        def insert = {
            em = EMF.createEntityManager()
            CommissionCertification commission = getCommissionCertification()
            em.persist(commission.head)
            em.persist(commission.weldSpecialist)
            em.persist(commission.ndtSpecialist)
            em.persist(commission.safetySpecialist)
            em.persist(commission)
            COMMISSION_ID = commission.id
            return em
        }
        callInTransaction(tx, insert)

        assert COMMISSION_ID != null

        def updCommission
        def update = {
            em = EMF.createEntityManager()
            updCommission = em.find(CommissionCertification.class, COMMISSION_ID)
            def teacher = getTeachers().find { it.surname == 'Новировский' }
            assert teacher != null
            updCommission.head = teacher
            em.persist(teacher)
            em.persist(updCommission)
            return em
        }
        callInTransaction(tx, update)

        def chkCommission
        def find = {
            em = EMF.createEntityManager()
            chkCommission = em.find(CommissionCertification.class, COMMISSION_ID)
            return em
        }
        callInTransaction(tx, find)

        assert chkCommission != null
        assert chkCommission.head != null
        assert chkCommission.head.surname == 'Новировский'
    }

    @Override
    @Test
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        EntityManager em = null
        def COMMISSION_ID
        def insert  = {
            em = EMF.createEntityManager()
            CommissionCertification commission = getCommissionCertification()
            em.persist(commission.head)
            em.persist(commission.weldSpecialist)
            em.persist(commission.ndtSpecialist)
            em.persist(commission.safetySpecialist)

            em.persist(commission)
            COMMISSION_ID = commission.id
            return em
        }
        callInTransaction(tx, insert)
        assert COMMISSION_ID != null

        def delCommission = null
        def delete = {
            em = EMF.createEntityManager()
            delCommission = em.find(CommissionCertification.class, COMMISSION_ID)
            em.remove(delCommission)
            return em
        }
        callInTransaction(tx, delete)
        def chkComnission = null
        def find = {
            em = EMF.createEntityManager()
            chkComnission = em.find(CommissionCertification.class, COMMISSION_ID)
            return em
        }
        callInTransaction(tx, find)
        assert chkComnission == null


    }

    @Override
    void equalsHashCodeTest() {

    }

    @Override
    void toStringTest() {

    }
}
