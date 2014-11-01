package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.ResolutionCertificationDao;
import com.lvg.weldercenter.models.ResolutionCertification;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class ResolutionCertificationDaoHiberImpl extends GenericDaoHiberImpl
        implements ResolutionCertificationDao {

    @Override
    public List<ResolutionCertification> getAll() {
        return getSession().createQuery("from ResolutionCertification").list();
    }

    @Override
    public ResolutionCertification get(Long id) {
        return (ResolutionCertification)getSession().get(ResolutionCertification.class, id);
    }

    @Override
    public void update(ResolutionCertification record) {
        getSession().update(record);
    }

    @Override
    public Long add(ResolutionCertification record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(ResolutionCertification record) {
        getSession().delete(record);
    }
}
