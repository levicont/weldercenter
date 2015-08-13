package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.TotalProtocolDao;
import com.lvg.weldercenter.models.TotalProtocol;
import com.lvg.weldercenter.services.TotalProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 03.02.15.
 */
@Transactional
public class TotalProtocolServiceHiberImpl implements TotalProtocolService {

    @Autowired
    TotalProtocolDao dao;

    public void setDao(TotalProtocolDao dao) {
        this.dao = dao;
    }

    @Override
    public List<TotalProtocol> getAll() {
        return dao.getAll();
    }

    @Override
    public TotalProtocol get(Long id) {
        return dao.get(id);
    }

    @Override
    public void update(TotalProtocol record) {
        dao.update(record);
    }

    @Override
    public Long insert(TotalProtocol record) {
        return dao.add(record);
    }

    @Override
    public void delete(TotalProtocol record) {
        dao.delete(record);
    }
}
