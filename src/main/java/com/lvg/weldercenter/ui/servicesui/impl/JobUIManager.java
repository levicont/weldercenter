package com.lvg.weldercenter.ui.servicesui.impl;

import com.lvg.weldercenter.models.Job;
import com.lvg.weldercenter.services.JobService;
import com.lvg.weldercenter.ui.entities.JobUI;
import com.lvg.weldercenter.ui.servicesui.JobServiceUI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Victor on 05.08.2015.
 */
public class JobUIManager implements JobServiceUI {

    @Autowired
    JobService jobService;

    @Override
    public Job getJobFromUIModel(JobUI jobUI) {
        if (jobUI == null)
        return null;
        Job job = jobService.get(jobUI.getId());
        if (job != null){
            updateJobFromUIModel(job, jobUI);
        }else {
            job = new Job();
            updateJobFromUIModel(job, jobUI);
        }
        return job;
    }

    private void updateJobFromUIModel(Job updJob, JobUI uiModel){
        updJob.setName(uiModel.getName());
    }
}
