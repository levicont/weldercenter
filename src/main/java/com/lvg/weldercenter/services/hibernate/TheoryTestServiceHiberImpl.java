package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.TheoryTestDao;
import com.lvg.weldercenter.models.TheoryTest;
import com.lvg.weldercenter.services.TheoryTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class TheoryTestServiceHiberImpl implements TheoryTestService {

    @Autowired
    private TheoryTestDao dao;

    public void setDao(TheoryTestDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<TheoryTest> getAll() {
        return dao.getAll();
    }

    @Override
    public TheoryTest get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(TheoryTest record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(TheoryTest record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(TheoryTest record) {
        dao.delete(record);
    }
}
