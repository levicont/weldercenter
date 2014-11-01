package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.SteelTypeDao;
import com.lvg.weldercenter.models.SteelType;
import com.lvg.weldercenter.services.SteelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class SteelTypeServiceHiberImpl implements SteelTypeService {

    @Autowired
    private SteelTypeDao dao;

    public void setDao(SteelTypeDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<SteelType> getAll() {
        return dao.getAll();
    }

    @Override
    public SteelType get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(SteelType record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(SteelType record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(SteelType record) {
        dao.delete(record);
    }
}
