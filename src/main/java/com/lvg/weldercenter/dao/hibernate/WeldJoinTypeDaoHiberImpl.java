package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.WeldJoinTypeDao;
import com.lvg.weldercenter.models.WeldJoinType;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor on 22.06.2015.
 */
@Repository
public class WeldJoinTypeDaoHiberImpl extends GenericDaoHiberImpl implements WeldJoinTypeDao{

    @Override
    public List<WeldJoinType> getAll() {
        return getSession().createQuery("from WeldJoinType").list();
    }

    @Override
    public WeldJoinType get(Long id) {
        return (WeldJoinType)getSession().get(WeldJoinType.class, id);
    }

    @Override
    public void update(WeldJoinType record) {
        getSession().update(record);
    }

    @Override
    public Long add(WeldJoinType record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(WeldJoinType record) {
        getSession().delete(record);
    }
}
