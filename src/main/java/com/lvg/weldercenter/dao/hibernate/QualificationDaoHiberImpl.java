package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.QualificationDao;
import com.lvg.weldercenter.models.Qualification;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class QualificationDaoHiberImpl extends GenericDaoHiberImpl
        implements QualificationDao{

    @Override
    public List<Qualification> getAll() {
        return getSession().createQuery("from Qualification").list();
    }

    @Override
    public Qualification get(Long id) {
        return (Qualification)getSession().get(Qualification.class, id);
    }

    @Override
    public void update(Qualification record) {
        getSession().update(record);
    }

    @Override
    public Long add(Qualification record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(Qualification record) {
        getSession().delete(record);
    }
}
