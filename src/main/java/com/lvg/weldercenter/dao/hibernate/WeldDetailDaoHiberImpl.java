package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.WeldDetailDao;
import com.lvg.weldercenter.models.WeldDetail;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class WeldDetailDaoHiberImpl extends GenericDaoHiberImpl
        implements WeldDetailDao {

    @Override
    public List<WeldDetail> getAll() {
        return getSession().createQuery("from WeldDetail").list();
    }

    @Override
    public WeldDetail get(Long id) {
        return (WeldDetail)getSession().get(WeldDetail.class, id);
    }

    @Override
    public void update(WeldDetail record) {
        getSession().update(record);
    }

    @Override
    public Long add(WeldDetail record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(WeldDetail record) {
        getSession().delete(record);
    }
}
