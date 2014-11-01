package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.WeldWireDao;
import com.lvg.weldercenter.models.WeldWire;
import com.lvg.weldercenter.services.WeldWireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class WeldWireServiceHiberImpl implements WeldWireService {

    @Autowired
    private WeldWireDao dao;

    public void setDao(WeldWireDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<WeldWire> getAll() {
        return dao.getAll();
    }

    @Override
    public WeldWire get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(WeldWire record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(WeldWire record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(WeldWire record) {
        dao.delete(record);
    }
}
