package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.MechanicalTestDao;
import com.lvg.weldercenter.models.MechanicalTest;
import com.lvg.weldercenter.services.MechanicalTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class MechanicalTestServiceHiberImpl implements MechanicalTestService {

    @Autowired
    private MechanicalTestDao dao;

    public void setDao(MechanicalTestDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<MechanicalTest> getAll() {
        return dao.getAll();
    }

    @Override
    public MechanicalTest get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(MechanicalTest record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(MechanicalTest record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(MechanicalTest record) {
        dao.delete(record);
    }
}
