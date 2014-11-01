package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.NDTDocumentDao;
import com.lvg.weldercenter.models.NDTDocument;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class NDTDocumentDaoHiberImpl extends GenericDaoHiberImpl
        implements NDTDocumentDao {

    @Override
    public List<NDTDocument> getAll() {
        return getSession().createQuery("from NDTDocument").list();
    }

    @Override
    public NDTDocument get(Long id) {
        return (NDTDocument)getSession().get(NDTDocument.class, id);
    }

    @Override
    public void update(NDTDocument record) {
        getSession().update(record);
    }

    @Override
    public Long add(NDTDocument record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(NDTDocument record) {
        getSession().delete(record);
    }
}
