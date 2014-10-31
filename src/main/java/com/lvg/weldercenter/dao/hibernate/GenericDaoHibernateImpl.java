package com.lvg.weldercenter.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Victor Levchenko (LVG Corp.) on 30.10.2014.
 */
@Repository
public abstract class GenericDaoHibernateImpl{

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
