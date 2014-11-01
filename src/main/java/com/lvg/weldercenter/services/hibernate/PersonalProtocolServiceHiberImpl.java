package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.PersonalProtocolDao;
import com.lvg.weldercenter.models.PersonalProtocol;
import com.lvg.weldercenter.services.PersonalProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class PersonalProtocolServiceHiberImpl implements PersonalProtocolService {
    @Autowired
    private PersonalProtocolDao dao;

    public void setDao(PersonalProtocolDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<PersonalProtocol> getAll() {
        return dao.getAll();
    }

    @Override
    public PersonalProtocol get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(PersonalProtocol record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(PersonalProtocol record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(PersonalProtocol record) {
        dao.delete(record);
    }
}
