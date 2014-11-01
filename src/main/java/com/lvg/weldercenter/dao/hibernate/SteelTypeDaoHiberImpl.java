package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.SteelTypeDao;
import com.lvg.weldercenter.models.SteelType;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class SteelTypeDaoHiberImpl extends GenericDaoHiberImpl
        implements SteelTypeDao {

    @Override
    public List<SteelType> getAll() {
        return getSession().createQuery("from SteelType").list();
    }

    @Override
    public SteelType get(Long id) {
        return (SteelType)getSession().get(SteelType.class, id);
    }

    @Override
    public void update(SteelType record) {
        getSession().update(record);
    }

    @Override
    public Long add(SteelType record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(SteelType record) {
        getSession().delete(record);
    }
}
