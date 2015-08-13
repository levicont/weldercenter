package com.lvg.weldercenter.services.hibernate;

import com.lvg.weldercenter.dao.OrganizationDao;
import com.lvg.weldercenter.models.Organization;
import com.lvg.weldercenter.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Victor Levchenko LVG Corp. on 31.10.14.
 */
@Transactional(readOnly = true)
public class OrganizationServiceHiberImpl implements OrganizationService {


    @Autowired
    private OrganizationDao dao;

    public void setDao(OrganizationDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<Organization> getAll() {
        return dao.getAll();
    }

    @Override
    public Organization get(Long id) {
        return dao.get(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void update(Organization record) {
        dao.update(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long insert(Organization record) {
        return dao.add(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Organization record) {
        dao.delete(record);
    }

    @Override
    public Organization getByName(String name) {
        List<Organization> list  = getAll();
        for (Organization org : list){
            if(name.equals(org.getName()))
                return org;
        }
        return null;
    }
}
