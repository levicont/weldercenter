package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.VisualTestDao;
import com.lvg.weldercenter.models.VisualTest;
import com.lvg.weldercenter.services.VisualTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class VisualTestServiceHiberImpl implements VisualTestService {

    @Autowired
    private VisualTestDao dao;

    public void setDao(VisualTestDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<VisualTest> getAll() {
        return dao.getAll();
    }

    @Override
    public VisualTest get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(VisualTest record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(VisualTest record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(VisualTest record) {
        dao.delete(record);
    }
}
