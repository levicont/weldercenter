package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.TopicDao;
import com.lvg.weldercenter.models.Topic;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */

@Repository
public class TopicDaoHiberImpl extends GenericDaoHiberImpl
        implements TopicDao {

    @Override
    public List<Topic> getAll() {
        return getSession().createQuery("from Topic").list();
    }

    @Override
    public Topic get(Long id) {
        return (Topic)getSession().get(Topic.class, id);
    }

    @Override
    public void update(Topic record) {
        getSession().update(record);
    }

    @Override
    public Long add(Topic record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(Topic record) {
        getSession().delete(record);
    }
}
