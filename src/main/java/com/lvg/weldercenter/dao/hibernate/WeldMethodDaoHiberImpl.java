package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.WeldGasDao;
import com.lvg.weldercenter.dao.WeldMethodDao;
import com.lvg.weldercenter.models.WeldMethod;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class WeldMethodDaoHiberImpl extends GenericDaoHiberImpl
        implements WeldMethodDao {

    @Override
    public List<WeldMethod> getAll() {
        return getSession().createQuery("from WeldMethod").list();
    }

    @Override
    public WeldMethod get(Long id) {
        return (WeldMethod)getSession().get(WeldMethod.class, id);
    }

    @Override
    public void update(WeldMethod record) {
        getSession().update(record);
    }

    @Override
    public Long add(WeldMethod record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(WeldMethod record) {
        getSession().delete(record);
    }
}
