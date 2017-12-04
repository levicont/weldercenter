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
    Job save(Job job) {
        return jobRepository.save(job)
    }

    @Override
    List<Job> getAll() {
        return jobRepository.findAll()
    }

    @Override
    Job get(Long id) {
        return jobRepository.findOne(id)
    }

    @Override
    void delete(Job job) {
        jobRepository.delete(job)
    }

    @Override
    Long count() {
        return jobRepository.count()
    }
}
