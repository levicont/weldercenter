package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.WeldGasDao;
import com.lvg.weldercenter.models.WeldGas;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class WeldGasDaoHiberImpl extends GenericDaoHiberImpl
        implements WeldGasDao {

    @Override
    public List<WeldGas> getAll() {
        return getSession().createQuery("from WeldGas").list();
    }

    @Override
    public WeldGas get(Long id) {
        return (WeldGas)getSession().get(WeldGas.class, id);
    }

    @Override
    public void update(WeldGas record) {
        getSession().update(record);
    }

    @Override
    public Long add(WeldGas record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(WeldGas record) {
        getSession().delete(record);
    }
}
