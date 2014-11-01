package com.lvg.weldercenter.todo;

import com.lvg.weldercenter.models.Job;
import com.lvg.weldercenter.services.JobService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 24.10.14.
 */
public class Launcher {
    public void launch(){
        String[] contextPaths = new String[]{"app-context-db.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(contextPaths);
        JobService jobService = (JobService)context.getBean("jobService");

        Job job = new Job();
        job.setName("Сварщик-СО2");

        //jobService.insert(job);

        job = jobService.get(1L);

        System.out.println(job.getName());

        List<Job> jobList = jobService.getAll();
        for (Job j : jobList){
            System.out.println("id: "+j.getJobId()+" type: "+j.getName());
        }

        job = jobList.get(4);
        job.setName("Сварщик - С2Н2 (ацетилен)");

        jobService.update(job);


    }
}
