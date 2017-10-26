package com.lvg.weldercenter.se.services

import com.lvg.weldercenter.se.models.Job

interface JobService {
    Job addJob(Job job)
    List<Job> getAll()

}