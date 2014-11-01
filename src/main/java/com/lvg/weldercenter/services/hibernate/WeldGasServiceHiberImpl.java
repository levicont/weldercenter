package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.WeldGasDao;
import com.lvg.weldercenter.models.WeldGas;
import com.lvg.weldercenter.services.WeldGasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class WeldGasServiceHiberImpl implements WeldGasService {

    @Autowired
    private WeldGasDao dao;

    public void setDao(WeldGasDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<WeldGas> getAll() {
        return dao.getAll();
    }

    @Override
    public WeldGas get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(WeldGas record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(WeldGas record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(WeldGas record) {
        dao.delete(record);
    }
}
