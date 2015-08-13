package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.WelderDao;
import com.lvg.weldercenter.models.Welder;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 29.10.2014.
 */
@Repository
public class WelderDaoHiberImpl extends GenericDaoHiberImpl implements WelderDao {


    //Implemented methods
    @Override
    public List<Welder> getAll() {
        return getSession().createQuery("from Welder").list();
    }

    @Override
    public Welder get(Long id) {
        return (Welder)getSession().get(Welder.class, id);
    }

    @Override
    public void update(Welder record) {
        getSession().update(record);
    }

    @Override
    public Long add(Welder record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(Welder record) {
        getSession().delete(record);
    }
}
