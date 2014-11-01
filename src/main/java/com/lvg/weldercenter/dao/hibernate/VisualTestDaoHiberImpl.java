package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.VisualTestDao;
import com.lvg.weldercenter.models.VisualTest;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class VisualTestDaoHiberImpl extends GenericDaoHiberImpl
        implements VisualTestDao {

    @Override
    public List<VisualTest> getAll() {
        return getSession().createQuery("from VisualTest").list();
    }

    @Override
    public VisualTest get(Long id) {
        return (VisualTest)getSession().get(VisualTest.class, id);
    }

    @Override
    public void update(VisualTest record) {
        getSession().update(record);
    }

    @Override
    public Long add(VisualTest record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(VisualTest record) {
        getSession().delete(record);
    }
}
