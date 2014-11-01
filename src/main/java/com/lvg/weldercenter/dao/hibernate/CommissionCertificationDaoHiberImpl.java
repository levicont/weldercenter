package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.CommissionCertificationDao;
import com.lvg.weldercenter.models.CommissionCertification;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class CommissionCertificationDaoHiberImpl extends GenericDaoHiberImpl
                            implements CommissionCertificationDao {

    @Override
    public List<CommissionCertification> getAll() {
        return getSession().createQuery("from CommissionCertification").list();
    }

    @Override
    public CommissionCertification get(Long id) {
        return (CommissionCertification)getSession().get(CommissionCertification.class, id);
    }

    @Override
    public void update(CommissionCertification record) {
        getSession().update(record);
    }

    @Override
    public Long add(CommissionCertification record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(CommissionCertification record) {
        getSession().delete(record);
    }
}
