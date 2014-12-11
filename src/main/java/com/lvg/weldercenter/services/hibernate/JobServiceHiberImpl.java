package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.JobDao;
import com.lvg.weldercenter.models.Job;
import com.lvg.weldercenter.models.Qualification;
import com.lvg.weldercenter.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 30.10.2014.
 */
@Transactional(readOnly = true)
public class JobServiceHiberImpl implements JobService {

    @Autowired
    private JobDao dao;

    public void setDao(JobDao dao) {
        this.dao = dao;
    }

    //Implemented methods


    @Override
    public List<Job> getAll() {
        return dao.getAll();
    }

    @Override
    public Job get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Job record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(Job record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Job record) {
        dao.delete(record);
    }

    @Override
    public Job getByName(String name) {
        List<Job> list  = getAll();
        for (Job job : list){
            if(name.equals(job.getName()))
                return job;
        }
        return null;
            }
}
