package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.WeldPatternDao;
import com.lvg.weldercenter.models.WeldPattern;
import com.lvg.weldercenter.services.WeldPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class WeldPatternServiceHiberImpl implements WeldPatternService {

    @Autowired
    private WeldPatternDao dao;

    public void setDao(WeldPatternDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<WeldPattern> getAll() {
        return dao.getAll();
    }

    @Override
    public WeldPattern get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(WeldPattern record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(WeldPattern record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(WeldPattern record) {
        dao.delete(record);
    }
}
