package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.TheoryTestDao;
import com.lvg.weldercenter.models.TheoryTest;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class TheoryTestDaoHiberImpl extends GenericDaoHiberImpl
        implements TheoryTestDao {

    @Override
    public List<TheoryTest> getAll() {
        return getSession().createQuery("from TheoryTest").list();
    }

    @Override
    public TheoryTest get(Long id) {
        return (TheoryTest)getSession().get(TheoryTest.class, id);
    }

    @Override
    public void update(TheoryTest record) {
        getSession().update(record);
    }

    @Override
    public Long add(TheoryTest record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(TheoryTest record) {
        getSession().delete(record);
    }
}
