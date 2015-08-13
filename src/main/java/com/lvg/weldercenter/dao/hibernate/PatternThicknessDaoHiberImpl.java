package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.PatternThicknessDao;
import com.lvg.weldercenter.models.PatternThickness;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class PatternThicknessDaoHiberImpl extends GenericDaoHiberImpl
        implements PatternThicknessDao {


    @Override
    public List<PatternThickness> getAll() {
        return getSession().createQuery("from PatternThickness").list();
    }

    @Override
    public PatternThickness get(Long id) {
        return (PatternThickness)getSession().get(PatternThickness.class, id);
    }

    @Override
    public void update(PatternThickness record) {
        getSession().update(record);
    }

    @Override
    public Long add(PatternThickness record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(PatternThickness record) {
        getSession().delete(record);
    }
}
