package com.lvg.weldercenter.se.test.services

import com.lvg.weldercenter.se.services.JobService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
class JobServiceTest extends GenericServiceTest{

    @Autowired
    JobService jobService

    @Test
    void addJobTest(){
        def job = getJob()
        job = jobService.addJob(job)
        assert job.id != null
    }

    @Test
    void getAllJobTest(){
        def job = getJob()
        job = jobService.addJob(job)
        assert job.id != null

        def list = jobService.getAll()
        assert list instanceof List
        assert list.size() == 1

    }
}
