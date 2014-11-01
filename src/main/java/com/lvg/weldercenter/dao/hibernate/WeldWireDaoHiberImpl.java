package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.WeldWireDao;
import com.lvg.weldercenter.models.WeldWire;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class WeldWireDaoHiberImpl extends GenericDaoHiberImpl
        implements WeldWireDao {

    @Override
    public List<WeldWire> getAll() {
        return getSession().createQuery("from WeldWire").list();
    }

    @Override
    public WeldWire get(Long id) {
        return (WeldWire)getSession().get(WeldWire.class, id);
    }

    @Override
    public void update(WeldWire record) {
        getSession().update(record);
    }

    @Override
    public Long add(WeldWire record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(WeldWire record) {
        getSession().delete(record);
    }
}
