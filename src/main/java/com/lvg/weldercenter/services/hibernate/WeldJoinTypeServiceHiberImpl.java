package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.WeldJoinTypeDao;
import com.lvg.weldercenter.dao.WeldPositionDao;
import com.lvg.weldercenter.models.WeldJoinType;
import com.lvg.weldercenter.services.WeldJoinTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor on 22.06.2015.
 */

@Transactional(readOnly = true)
public class WeldJoinTypeServiceHiberImpl implements WeldJoinTypeService {

    @Autowired
    private WeldJoinTypeDao dao;

    public void setDao(WeldJoinTypeDao dao){
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<WeldJoinType> getAll() {
        return dao.getAll();
    }

    @Override
    public WeldJoinType get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(WeldJoinType record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(WeldJoinType record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(WeldJoinType record) {
        dao.delete(record);
    }
}
