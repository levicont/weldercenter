package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.EvaluationDao;
import com.lvg.weldercenter.models.Evaluation;
import com.lvg.weldercenter.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class EvaluationServiceHiberImpl implements EvaluationService{

    @Autowired
    private EvaluationDao dao;

    public void setDao(EvaluationDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<Evaluation> getAll() {
        return dao.getAll();
    }

    @Override
    public Evaluation get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Evaluation record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(Evaluation record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Evaluation record) {
        dao.delete(record);
    }
}
