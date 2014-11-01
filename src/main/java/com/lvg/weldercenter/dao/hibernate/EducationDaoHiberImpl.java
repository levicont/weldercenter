package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.EducationDao;
import com.lvg.weldercenter.models.Education;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class EducationDaoHiberImpl extends GenericDaoHiberImpl
        implements EducationDao {

    @Override
    public List<Education> getAll() {
        return getSession().createQuery("from Education").list();
    }

    @Override
    public Education get(Long id) {
        return (Education)getSession().get(Education.class, id);
    }

    @Override
    public void update(Education record) {
        getSession().update(record);
    }

    @Override
    public Long add(Education record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(Education record) {
        getSession().delete(record);
    }
}
