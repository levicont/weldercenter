package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.Organization
import com.lvg.weldercenter.se.repositories.OrganizationRepository
import com.lvg.weldercenter.se.services.OrganizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationRepository repository

    @Override
    Organization save(Organization organization) {
        return repository.save(organization)
    }

    @Override
    Organization get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<Organization> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(Organization organization) {
        repository.delete(organization)
    }
}
