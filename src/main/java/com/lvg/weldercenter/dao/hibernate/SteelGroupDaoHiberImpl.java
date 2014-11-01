package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.SteelGroupDao;
import com.lvg.weldercenter.models.SteelGroup;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class SteelGroupDaoHiberImpl extends GenericDaoHiberImpl
        implements SteelGroupDao {

    @Override
    public List<SteelGroup> getAll() {
        return getSession().createQuery("from SteelGroup").list();
    }

    @Override
    public SteelGroup get(Long id) {
        return (SteelGroup)getSession().get(SteelGroup.class, id);
    }

    @Override
    public void update(SteelGroup record) {
        getSession().update(record);
    }

    @Override
    public Long add(SteelGroup record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(SteelGroup record) {
        getSession().delete(record);
    }
}
