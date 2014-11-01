package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.WeldMethodDao;
import com.lvg.weldercenter.models.WeldMethod;
import com.lvg.weldercenter.services.WeldMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class WeldMethodServiceHiberImpl implements WeldMethodService {

    @Autowired
    private WeldMethodDao dao;

    public void setDao(WeldMethodDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<WeldMethod> getAll() {
        return dao.getAll();
    }

    @Override
    public WeldMethod get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(WeldMethod record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(WeldMethod record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(WeldMethod record) {
        dao.delete(record);
    }
}
