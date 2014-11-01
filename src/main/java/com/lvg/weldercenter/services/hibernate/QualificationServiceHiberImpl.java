package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.QualificationDao;
import com.lvg.weldercenter.models.Qualification;
import com.lvg.weldercenter.services.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class QualificationServiceHiberImpl implements QualificationService {

    @Autowired
    private QualificationDao dao;

    public void setDao(QualificationDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<Qualification> getAll() {
        return dao.getAll();
    }

    @Override
    public Qualification get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Qualification record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(Qualification record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Qualification record) {
        dao.delete(record);
    }
}
