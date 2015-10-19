package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.PatternThicknessDao;
import com.lvg.weldercenter.models.PatternThickness;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class PatternThicknessDaoHiberImpl extends GenericDaoHiberImpl
        implements PatternThicknessDao {

    private class ThicknessComparator implements Comparator<PatternThickness>{
        @Override
        public int compare(PatternThickness o1, PatternThickness o2) {
            if(o1.getThickness()>o2.getThickness())
                return 1;
            if (o1.getThickness()<o2.getThickness())
                return -1;
            return 0;
        }
    }
    @Override
    public List<PatternThickness> getAll() {
        List<PatternThickness> result = getSession().createQuery("from PatternThickness").list();
        result.sort(new ThicknessComparator());
        return result;
    }

    @Override
    public PatternThickness get(Long id) {
        return (PatternThickness)getSession().get(PatternThickness.class, id);
    }

    @Override
    public void update(PatternThickness record) {
        getSession().update(record);
    }

    @Override
    public Long add(PatternThickness record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(PatternThickness record) {
        getSession().delete(record);
    }
}
