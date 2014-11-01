package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.ResolutionCertificationDao;
import com.lvg.weldercenter.models.ResolutionCertification;
import com.lvg.weldercenter.services.ResolutionCertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class ResolutionCertificationServiceHiberImpl implements ResolutionCertificationService {
    @Autowired
    private ResolutionCertificationDao dao;

    public void setDao(ResolutionCertificationDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<ResolutionCertification> getAll() {
        return dao.getAll();
    }

    @Override
    public ResolutionCertification get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(ResolutionCertification record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(ResolutionCertification record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(ResolutionCertification record) {
        dao.delete(record);
    }
}
