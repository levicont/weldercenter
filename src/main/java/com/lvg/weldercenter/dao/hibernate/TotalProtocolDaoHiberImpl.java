package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.TotalProtocolDao;
import com.lvg.weldercenter.models.TotalProtocol;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 03.02.15.
 */
@Repository
public class TotalProtocolDaoHiberImpl extends GenericDaoHiberImpl implements TotalProtocolDao {

    @Override
    public List<TotalProtocol> getAll() {
        return getSession().createQuery("from TotalProtocol").list();
    }

    @Override
    public TotalProtocol get(Long id) {
        return (TotalProtocol)getSession().get(TotalProtocol.class, id);
    }

    @Override
    public void update(TotalProtocol record) {
        getSession().update(record);
    }

    @Override
    public Long add(TotalProtocol record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(TotalProtocol record) {
        getSession().delete(record);
    }
}
