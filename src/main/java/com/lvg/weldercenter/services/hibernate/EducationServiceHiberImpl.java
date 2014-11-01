package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.EducationDao;
import com.lvg.weldercenter.models.Education;
import com.lvg.weldercenter.services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class EducationServiceHiberImpl implements EducationService {

    @Autowired
    private EducationDao dao;

    public void setDao(EducationDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<Education> getAll() {
        return dao.getAll();
    }

    @Override
    public Education get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Education record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(Education record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Education record) {
        dao.delete(record);
    }
}
