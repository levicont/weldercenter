package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.CommissionCertificationDao;
import com.lvg.weldercenter.models.CommissionCertification;
import com.lvg.weldercenter.services.CommissionCertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class CommissionCertificationServiceHiberImpl implements CommissionCertificationService {

    @Autowired
    private CommissionCertificationDao dao;

    public void setDao(CommissionCertificationDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<CommissionCertification> getAll() {
        return dao.getAll();
    }

    @Override
    public CommissionCertification get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(CommissionCertification record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(CommissionCertification record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(CommissionCertification record) {
        dao.delete(record);
    }
}
