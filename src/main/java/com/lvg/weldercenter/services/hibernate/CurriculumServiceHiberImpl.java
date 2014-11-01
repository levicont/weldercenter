package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.CurriculumDao;
import com.lvg.weldercenter.models.Curriculum;
import com.lvg.weldercenter.services.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class CurriculumServiceHiberImpl implements CurriculumService {

    @Autowired
    private CurriculumDao dao;

    public void setDao(CurriculumDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<Curriculum> getAll() {
        return dao.getAll();
    }

    @Override
    public Curriculum get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Curriculum record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(Curriculum record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Curriculum record) {
        dao.delete(record);
    }
}
