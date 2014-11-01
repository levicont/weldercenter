package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.WeldPatternDao;
import com.lvg.weldercenter.models.WeldPattern;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class WeldPatternDaoHiberImpl extends GenericDaoHiberImpl
        implements WeldPatternDao {

    @Override
    public List<WeldPattern> getAll() {
        return getSession().createQuery("from WeldPattern").list();
    }

    @Override
    public WeldPattern get(Long id) {
        return (WeldPattern)getSession().get(WeldPattern.class, id);
    }

    @Override
    public void update(WeldPattern record) {
        getSession().update(record);

    }

    @Override
    public Long add(WeldPattern record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(WeldPattern record) {
        getSession().delete(record);
    }
}
