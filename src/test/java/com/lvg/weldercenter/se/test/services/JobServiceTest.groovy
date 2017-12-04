package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.models.Job
import com.lvg.weldercenter.se.services.JobService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class JobServiceTest extends GenericServiceTest{

    @Autowired
    JobService jobService

    void saveTest(){
        def JOB_ID
        def job = getJob()
        job = jobService.save(job)
        JOB_ID = job.id
        assert JOB_ID != null

        Job updJob = jobService.get(JOB_ID)
        assert updJob != null
        assert updJob instanceof Job
        updJob.name = 'сварщик'
        jobService.save(updJob)

        Job chkJob = jobService.get(JOB_ID)
        assert chkJob != null
        assert chkJob instanceof Job
        assert chkJob.name == 'сварщик'
    }

    void getAllTest(){
        def job = getJob()
        job = jobService.save(job)
        assert job.id != null

        def list = jobService.getAll()
        assert list instanceof List
        assert list.size() == 1

    }

    @Override
    void countTest() {
        def job = getJob()
        job = jobService.save(job)
        assert job.id != null

        def count = jobService.count()
        assert count instanceof Long
        assert count == 1
    }

    @Override
    void deleteTest() {
        def JOB_ID
        def job = getJob()
        job = jobService.save(job)
        JOB_ID = job.id
        assert JOB_ID != null

        Job delJob = jobService.get(JOB_ID)
        assert delJob != null
        assert delJob instanceof Job
        jobService.delete(delJob)

        Job chkJob = jobService.get(JOB_ID)
        assert chkJob == null

    }

    @Override
    void getTest() {
        def JOB_ID
        def job = getJob()
        job = jobService.save(job)
        JOB_ID = job.id
        assert JOB_ID != null

        Job chkJob = jobService.get(JOB_ID)
        assert chkJob != null
        assert chkJob instanceof Job
    }
}
