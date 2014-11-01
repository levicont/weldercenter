package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.ElectrodeDao;
import com.lvg.weldercenter.models.Electrode;
import com.lvg.weldercenter.services.ElectrodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class ElectrodeServiceHiberImpl implements ElectrodeService {
    @Autowired
    private ElectrodeDao dao;

    public void setDao(ElectrodeDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<Electrode> getAll() {
        return dao.getAll();
    }

    @Override
    public Electrode get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Electrode record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(Electrode record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Electrode record) {
        dao.delete(record);
    }
}
