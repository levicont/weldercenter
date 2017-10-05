package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Job
import org.junit.Test

import javax.persistence.EntityManager
import javax.transaction.UserTransaction

/**
 * Created by Victor on 05.10.2017.
 */
class JobTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Job job = getJob()
        em.persist(job)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        def list = em.createQuery('select j from Job j').getResultList()
        tx.commit()

        assert list.size() == 1
        job = list.get(0)


        assert job.id != null
        assert job.name == 'электросварщик'

    }

    @Override
    @Test
    void updateItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Job job = getJob()
        em.persist(job)
        tx.commit()

        assert job.id != null
        def JOB_ID = job.id
        tx.begin()
        em = EMF.createEntityManager()
        Job jobUpd = em.find(Job.class, JOB_ID)
        jobUpd.name = 'газосварщик'
        em.persist(jobUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Job chkJob = em.find(Job.class, JOB_ID)
        assert chkJob.name == 'газосварщик'
        tx.commit()
    }

    @Override
    @Test
    void deleteItemTest() {
        UserTransaction tx = TMS.getUserTransaction()
        tx.begin()
        EntityManager em = EMF.createEntityManager()
        Job job = getJob()
        em.persist(job)
        tx.commit()

        assert job.id != null
        def JOB_ID = job.id

        tx.begin()
        em = EMF.createEntityManager()
        Job jobUpd = em.find(Job.class, JOB_ID)
        em.remove(jobUpd)
        tx.commit()

        tx.begin()
        em = EMF.createEntityManager()
        Job chkJob = em.find(Job.class, JOB_ID)
        assert chkJob == null
        tx.commit()

    }

    @Override
    @Test
    void equalsHashCodeTest() {
        def job1 = getJob()
        def job2 = getJob()

        assert job1 == job2

        job1.id = 100
        job2.id = 101

        assert job1 != job2

        def list = new HashSet<Job>()
        list.add(job1)
        job2.id = 100
        list.add(job2)

        assert list.size() == 1

    }

    @Override
    @Test
    void toStringTest() {
        def job = getJob()
        job.name = 'газосварщик'
        assert job.toString() == 'газосварщик'
    }
}
