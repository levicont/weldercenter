package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.WelderDao;
import com.lvg.weldercenter.models.Welder;
import com.lvg.weldercenter.services.WelderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko (LVG Corp.) on 28.10.2014.
 */
@Transactional(readOnly = true)
public class WelderServiceHiberImpl implements WelderService {

    @Autowired
    private WelderDao dao;

    private void setDao(WelderDao dao){
        this.dao = dao;
    }

    @Override
    public List<Welder> getAllBySurname(String surname) {
        return null;
    }

    @Override
    public List<Welder> getAll() {
        return dao.getAll();
    }

    @Override
    public Welder get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Welder record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(Welder record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Welder record) {
        dao.delete(record);
    }
}
