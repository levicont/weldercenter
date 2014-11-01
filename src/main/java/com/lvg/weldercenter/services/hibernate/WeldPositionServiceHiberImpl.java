package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.WeldPositionDao;
import com.lvg.weldercenter.models.WeldPosition;
import com.lvg.weldercenter.services.WeldPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class WeldPositionServiceHiberImpl implements WeldPositionService {

    @Autowired
    private WeldPositionDao dao;

    public void setDao(WeldPositionDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<WeldPosition> getAll() {
        return dao.getAll();
    }

    @Override
    public WeldPosition get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(WeldPosition record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(WeldPosition record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(WeldPosition record) {
        dao.delete(record);
    }
}
