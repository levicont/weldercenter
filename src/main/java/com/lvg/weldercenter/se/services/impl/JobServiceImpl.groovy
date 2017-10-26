package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.Job
import com.lvg.weldercenter.se.repositories.JobRepository
import com.lvg.weldercenter.se.services.JobService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class JobServiceImpl implements JobService{

    @Autowired
    JobRepository jobRepository

    @Override
    Job addJob(Job job) {
        return jobRepository.save(job)
    }

    @Override
    List<Job> getAll() {
        return jobRepository.findAll()
    }
}
