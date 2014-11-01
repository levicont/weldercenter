package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.CurriculumDao;
import com.lvg.weldercenter.models.Curriculum;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class CurriculumDaoHiberImpl extends GenericDaoHiberImpl
            implements CurriculumDao{

    @Override
    public List<Curriculum> getAll() {
        return getSession().createQuery("from Curriculum").list();
    }

    @Override
    public Curriculum get(Long id) {
        return (Curriculum)getSession().get(Curriculum.class, id);
    }

    @Override
    public void update(Curriculum record) {
        getSession().update(record);
    }

    @Override
    public Long add(Curriculum record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(Curriculum record) {
        getSession().delete(record);
    }
}
