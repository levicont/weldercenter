package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.SectionDao;
import com.lvg.weldercenter.models.Section;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */

@Repository
public class SectionDaoHiberImpl extends GenericDaoHiberImpl
        implements SectionDao {

    @Override
    public List<Section> getAll() {
        return getSession().createQuery("from Section").list();
    }

    @Override
    public Section get(Long id) {
        return (Section)getSession().get(Section.class, id);
    }

    @Override
    public void update(Section record) {
        getSession().update(record);
    }

    @Override
    public Long add(Section record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(Section record) {
        getSession().delete(record);
    }
}
