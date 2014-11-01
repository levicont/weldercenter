package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.PatternDiameterDao;
import com.lvg.weldercenter.models.PatternDiameter;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class PatternDiameterDaoHiberImpl extends GenericDaoHiberImpl
        implements PatternDiameterDao {

    @Override
    public List<PatternDiameter> getAll() {
        return getSession().createQuery("from PatternDiameter").list();
    }

    @Override
    public PatternDiameter get(Long id) {
        return (PatternDiameter)getSession().get(PatternDiameter.class, id);
    }

    @Override
    public void update(PatternDiameter record) {
        getSession().update(record);
    }

    @Override
    public Long add(PatternDiameter record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(PatternDiameter record) {
        getSession().delete(record);
    }
}
