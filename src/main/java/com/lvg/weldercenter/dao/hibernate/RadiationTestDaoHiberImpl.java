package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.RadiationTestDao;
import com.lvg.weldercenter.models.RadiationTest;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class RadiationTestDaoHiberImpl extends GenericDaoHiberImpl
        implements RadiationTestDao {

    @Override
    public List<RadiationTest> getAll() {
        return getSession().createQuery("from RadiationTest").list();
    }

    @Override
    public RadiationTest get(Long id) {
        return (RadiationTest)getSession().get(RadiationTest.class, id);
    }

    @Override
    public void update(RadiationTest record) {
        getSession().update(record);
    }

    @Override
    public Long add(RadiationTest record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(RadiationTest record) {
        getSession().delete(record);
    }
}
