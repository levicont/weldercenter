package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.JobDao;
import com.lvg.weldercenter.models.Job;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 30.10.2014.
 */
@Repository
public class JobDaoHibernateImpl extends GenericDaoHibernateImpl implements JobDao {


    //Implemented methods

    @Override
    public List<Job> getAll() {
        return getSession().createQuery("from Job").list();
    }

    @Override
    public Job get(Long id) {
        return (Job)getSession().get(Job.class,id);
    }

    @Override
    public void update(Job record) {
        getSession().update(record);
    }

    @Override
    public void add(Job record) {
        getSession().save(record);
    }

    @Override
    public void delete(Job record) {
        getSession().delete(record);
    }
}
