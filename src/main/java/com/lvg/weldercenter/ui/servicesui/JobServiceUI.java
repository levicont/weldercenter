package com.lvg.weldercenter.ui.servicesui;

import com.lvg.weldercenter.models.Job;
import com.lvg.weldercenter.ui.entities.JobUI;

/**
 * Created by Victor on 05.08.2015.
 */
public interface JobServiceUI {
    public Job getJobFromUIModel(JobUI jobUI);
}
