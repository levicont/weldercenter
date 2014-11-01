package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.PersonalProtocolDao;
import com.lvg.weldercenter.models.PersonalProtocol;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class PersonalProtocolDaoHiberImpl extends GenericDaoHiberImpl
        implements PersonalProtocolDao {

    @Override
    public List<PersonalProtocol> getAll() {
        return getSession().createQuery("from PersonalProtocol").list();
    }

    @Override
    public PersonalProtocol get(Long id) {
        return (PersonalProtocol)getSession().get(PersonalProtocol.class, id);
    }

    @Override
    public void update(PersonalProtocol record) {
        getSession().update(record);
    }

    @Override
    public Long add(PersonalProtocol record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(PersonalProtocol record) {
        getSession().delete(record);
    }
}
