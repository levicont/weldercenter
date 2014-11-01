package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.ElectrodeDao;
import com.lvg.weldercenter.models.Electrode;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class ElectrodeDaoHiberImpl extends GenericDaoHiberImpl
        implements ElectrodeDao {

    @Override
    public List<Electrode> getAll() {
        return getSession().createQuery("from Electrode").list();
    }

    @Override
    public Electrode get(Long id) {
        return (Electrode)getSession().get(Electrode.class, id);
    }

    @Override
    public void update(Electrode record) {
        getSession().update(record);
    }

    @Override
    public Long add(Electrode record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(Electrode record) {
        getSession().delete(record);
    }
}
