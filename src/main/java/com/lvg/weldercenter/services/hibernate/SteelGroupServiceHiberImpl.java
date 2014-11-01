package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.SteelGroupDao;
import com.lvg.weldercenter.models.SteelGroup;
import com.lvg.weldercenter.services.SteelGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class SteelGroupServiceHiberImpl implements SteelGroupService{


    @Autowired
    private SteelGroupDao dao;

    public void setDao(SteelGroupDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<SteelGroup> getAll() {
        return dao.getAll();
    }

    @Override
    public SteelGroup get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(SteelGroup record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(SteelGroup record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(SteelGroup record) {
        dao.delete(record);
    }
}
