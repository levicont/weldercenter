package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.PatternDiameterDao;
import com.lvg.weldercenter.models.PatternDiameter;
import com.lvg.weldercenter.services.PatternDiameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class PatternDiameterServiceHiberImpl implements PatternDiameterService {


    @Autowired
    private PatternDiameterDao dao;

    public void setDao(PatternDiameterDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<PatternDiameter> getAll() {
        return dao.getAll();
    }

    @Override
    public PatternDiameter get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(PatternDiameter record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(PatternDiameter record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(PatternDiameter record) {
        dao.delete(record);
    }
}
