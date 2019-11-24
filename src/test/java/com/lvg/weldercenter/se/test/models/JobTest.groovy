package com.lvg.weldercenter.se.test.models

import com.lvg.weldercenter.se.models.Job
import com.lvg.weldercenter.se.services.JobService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class JobTest extends GenericModelTest {

    @Autowired
    JobService jobService

    @Override
    @Test
    void insertItemTest() {
        def JOB_ID

        Job job = getJob()
        jobService.save(job)
        JOB_ID = job.id





        def chkJob = jobService.get(JOB_ID)
        assert chkJob.id != null
        assert chkJob.name == 'электросварщик'

    }

    @Override
    @Test
    void updateItemTest() {
        def JOB_ID


        Job job = getJob()
        jobService.save(job)
        JOB_ID = job.id

        assert JOB_ID != null


        Job jobUpd = jobService.get(JOB_ID)
        jobUpd.name = 'газосварщик'
        jobService.save(jobUpd)



        Job chkJob = jobService.get(JOB_ID)
        assert chkJob.name == 'газосварщик'

    }

    @Override
    @Test
    void deleteItemTest() {
        def JOB_ID


        Job job = getJob()
        jobService.save(job)
        JOB_ID = job.id



        Job jobUpd = jobService.get(JOB_ID)
        jobService.delete(jobUpd)


        Job chkJob = jobService.get(JOB_ID)
        assert chkJob == null
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
