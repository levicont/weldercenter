package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.PatternThicknessDao;
import com.lvg.weldercenter.models.PatternThickness;
import com.lvg.weldercenter.services.PatternThicknessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class PatternThicknessServiceHiberImpl implements PatternThicknessService {

    @Autowired
    private PatternThicknessDao dao;

    public void setDao(PatternThicknessDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<PatternThickness> getAll() {
        return dao.getAll();
    }

    @Override
    public PatternThickness get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(PatternThickness record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(PatternThickness record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(PatternThickness record) {
        dao.delete(record);
    }
}
