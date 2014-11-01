package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.WeldDetailDao;
import com.lvg.weldercenter.models.WeldDetail;
import com.lvg.weldercenter.services.WeldDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class WeldDetailServiceHiberImpl implements WeldDetailService {

    @Autowired
    private WeldDetailDao dao;

    public void setDao(WeldDetailDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<WeldDetail> getAll() {
        return dao.getAll();
    }

    @Override
    public WeldDetail get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(WeldDetail record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(WeldDetail record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(WeldDetail record) {
        dao.delete(record);
    }
}
