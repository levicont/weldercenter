package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.WeldPositionDao;
import com.lvg.weldercenter.models.WeldPosition;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class WeldPositionDaoHiberImpl extends GenericDaoHiberImpl
        implements WeldPositionDao {


    @Override
    public List<WeldPosition> getAll() {
        return getSession().createQuery("from WeldPosition").list();
    }

    @Override
    public WeldPosition get(Long id) {
        return (WeldPosition)getSession().get(WeldPosition.class, id);
    }

    @Override
    public void update(WeldPosition record) {
        getSession().update(record);
    }

    @Override
    public Long add(WeldPosition record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(WeldPosition record) {
        getSession().delete(record);
    }
}
