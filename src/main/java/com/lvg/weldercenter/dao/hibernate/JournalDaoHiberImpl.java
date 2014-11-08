package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.JournalDao;
import com.lvg.weldercenter.models.Journal;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 02.11.14.
 */
@Repository
public class JournalDaoHiberImpl extends GenericDaoHiberImpl
            implements JournalDao{

    @Override
    public List<Journal> getAll() {
        return getSession().createQuery("from Journal").list();
    }

    @Override
    public Journal get(Long id) {
        return (Journal)getSession().get(Journal.class, id);
    }

    @Override
    public void update(Journal record) {
        getSession().update(record);
    }

    @Override
    public Long add(Journal record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(Journal record) {
        getSession().delete(record);
    }
}
