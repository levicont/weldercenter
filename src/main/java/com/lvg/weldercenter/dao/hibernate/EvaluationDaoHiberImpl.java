package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.EvaluationDao;
import com.lvg.weldercenter.models.Evaluation;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */

@Repository
public class EvaluationDaoHiberImpl extends GenericDaoHiberImpl
                implements EvaluationDao{

    @Override
    public List<Evaluation> getAll() {
        return getSession().createQuery("from Evaluation").list();
    }

    @Override
    public Evaluation get(Long id) {
        return (Evaluation)getSession().get(Evaluation.class, id);
    }

    @Override
    public void update(Evaluation record) {
        getSession().update(record);
    }

    @Override
    public Long add(Evaluation record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(Evaluation record) {
        getSession().delete(record);
    }
}
