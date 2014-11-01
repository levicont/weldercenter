package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.RadiationTestDao;
import com.lvg.weldercenter.models.RadiationTest;
import com.lvg.weldercenter.services.RadiationTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class RadiationTestServiceHiberImpl implements RadiationTestService {

    @Autowired
    private RadiationTestDao dao;

    public void setDao(RadiationTestDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<RadiationTest> getAll() {
        return dao.getAll();
    }

    @Override
    public RadiationTest get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(RadiationTest record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(RadiationTest record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(RadiationTest record) {
        dao.delete(record);
    }
}
