package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.TeacherDao;
import com.lvg.weldercenter.models.Teacher;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class TeacherDaoHiberImpl extends GenericDaoHiberImpl
        implements TeacherDao {

    @Override
    public List<Teacher> getAll() {
        return getSession().createQuery("from Teacher").list();
    }

    @Override
    public Teacher get(Long id) {
        return (Teacher)getSession().get(Teacher.class, id);
    }

    @Override
    public void update(Teacher record) {
        getSession().update(record);

    }

    @Override
    public Long add(Teacher record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(Teacher record) {
        getSession().delete(record);
    }
}
