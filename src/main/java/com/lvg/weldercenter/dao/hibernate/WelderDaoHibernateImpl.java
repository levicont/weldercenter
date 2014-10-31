package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.WelderDao;
import com.lvg.weldercenter.models.Welder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 29.10.2014.
 */
@Repository
public class WelderDaoHibernateImpl extends GenericDaoHibernateImpl implements WelderDao {


    //Implemented methods
    @Override
    public List<Welder> getAll() {
        return new ArrayList<Welder>();
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
    public void add(Welder record) {
        getSession().save(record);
    }

    @Override
    public void delete(Welder record) {
        getSession().delete(record);
    }
}
