package com.lvg.weldercenter.dao.hibernate;

import com.lvg.weldercenter.dao.OrganizationDao;
import com.lvg.weldercenter.models.Organization;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Repository
public class OrganizationDaoHiberImpl extends GenericDaoHiberImpl
        implements OrganizationDao {

    @Override
    public List<Organization> getAll() {
        return getSession().createQuery("from Organization").list();
    }

    @Override
    public Organization get(Long id) {
        return (Organization)getSession().get(Organization.class, id);
    }

    @Override
    public void update(Organization record) {
        getSession().update(record);
    }

    @Override
    public Long add(Organization record) {
        getSession().save(record);
        return ((BigInteger)getSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
    }

    @Override
    public void delete(Organization record) {
        getSession().delete(record);
    }
}
