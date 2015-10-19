package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.NDTDocumentDao;
import com.lvg.weldercenter.models.NDTDocument;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class NDTDocumentDaoHiberImpl extends GenericDaoHiberImpl
        implements NDTDocumentDao {

    private class NDTDocumentsComparator implements Comparator<NDTDocument>{
        @Override
        public int compare(NDTDocument o1, NDTDocument o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    @Override
    public List<NDTDocument> getAll() {
        List<NDTDocument> result = getSession().createQuery("from NDTDocument").list();
        result.sort(new NDTDocumentsComparator());
        return result;
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
