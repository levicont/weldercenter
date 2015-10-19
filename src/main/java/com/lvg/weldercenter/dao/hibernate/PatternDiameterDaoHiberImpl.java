package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.PatternDiameterDao;
import com.lvg.weldercenter.models.PatternDiameter;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class PatternDiameterDaoHiberImpl extends GenericDaoHiberImpl
        implements PatternDiameterDao {

    private class DiameterComparator implements Comparator<PatternDiameter> {
        @Override
        public int compare(PatternDiameter o1, PatternDiameter o2) {
            if(o1.getDiameter()>o2.getDiameter())
                return 1;
            if (o1.getDiameter()<o2.getDiameter())
                return -1;
            return 0;
        }
    }

    @Override
    public List<PatternDiameter> getAll() {
        List<PatternDiameter> result = getSession().createQuery("from PatternDiameter").list();
        result.sort(new DiameterComparator());
        return result;
    }

    @Override
    public PatternDiameter get(Long id) {
        return (PatternDiameter)getSession().get(PatternDiameter.class, id);
    }

    @Override
    public void update(PatternDiameter record) {
        getSession().update(record);
    }

    @Override
    public Long add(PatternDiameter record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(PatternDiameter record) {
        getSession().delete(record);
    }
}
