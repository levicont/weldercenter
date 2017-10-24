package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Job
import org.junit.Test

class JobTest extends GenericModelTest{

    @Override
    @Test
    void insertItemTest() {
        def JOB_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Job job = getJob()
            em.persist(job)
            JOB_ID = job.id
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            def chkJob = em.find(Job.class, JOB_ID)
            assert chkJob.id != null
            assert chkJob.name == 'электросварщик'
            return em
        }
    }

    @Override
    @Test
    void updateItemTest() {
        def JOB_ID
        callInTransaction {
            def em = EMF.createEntityManager()
            Job job = getJob()
            em.persist(job)
            JOB_ID = job.id
            return em
        }
        assert JOB_ID != null

        callInTransaction {
            def em = EMF.createEntityManager()
            Job jobUpd = em.find(Job.class, JOB_ID)
            jobUpd.name = 'газосварщик'
            em.persist(jobUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Job chkJob = em.find(Job.class, JOB_ID)
            assert chkJob.name == 'газосварщик'
            return em
        }
    }

    @Override
    @Test
    void deleteItemTest() {
        def JOB_ID

        callInTransaction {
            def em = EMF.createEntityManager()
            Job job = getJob()
            em.persist(job)
            JOB_ID = job.id
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Job jobUpd = em.find(Job.class, JOB_ID)
            em.remove(jobUpd)
            return em
        }

        callInTransaction {
            def em = EMF.createEntityManager()
            Job chkJob = em.find(Job.class, JOB_ID)
            assert chkJob == null
            return em
        }
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
