package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.MechanicalTestDao;
import com.lvg.weldercenter.models.MechanicalTest;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class MechanicalTestDaoHiberImpl extends GenericDaoHiberImpl
        implements MechanicalTestDao {
    @Override
    public List<MechanicalTest> getAll() {
        return getSession().createQuery("from MechanicalTest").list();
    }

    @Override
    public MechanicalTest get(Long id) {
        return (MechanicalTest)getSession().get(MechanicalTest.class, id);
    }

    @Override
    public void update(MechanicalTest record) {
        getSession().update(record);
    }

    @Override
    public Long add(MechanicalTest record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(MechanicalTest record) {
        getSession().delete(record);
    }
}
