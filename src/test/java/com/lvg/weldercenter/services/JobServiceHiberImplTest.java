package com.lvg.weldercenter.services;


import com.lvg.weldercenter.models.Job;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Victor Levchenko LVG Corp. on 01.11.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context-db-test.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JobServiceHiberImplTest {

    @Autowired
    private JobService jobService;

    @Test
    public void testGet(){
        Job job = jobService.get(1L);
        assertNotNull(job);
    }

    @Test
    public void testGetAll(){
        List<Job> jobs = jobService.getAll();
        assertNotNull(jobs);
    }

    @Test
    public void testGetByName(){
        Job job = jobService.get(1l);
        Job job1 = jobService.getByName(job.getName());
        assertNotNull(job1);
        assertEquals(job.getJobId(), job1.getJobId());
    }

    @Test
    @Transactional
    public void testDelete(){
        Job job = jobService.get(1L);
        jobService.delete(job);

        job = jobService.get(1L);

        assertNull(job);
    }

    @Test
    @Transactional
    public void testUpdate(){
        Job job = jobService.get(1L);
        job.setName("Сварщик СО2");

        jobService.update(job);

        job = jobService.get(1L);
        assertEquals("Сварщик СО2", job.getName());
    }

    @Test
    @Transactional
    public void testInsert(){
        Job job = new Job();
        job.setName("Сварщик СО2");

        Long id = jobService.insert(job);


        assertNotNull(id);
        job = jobService.get(id);

        assertNotNull(job);
        assertEquals("Сварщик СО2", job.getName());
    }
}
