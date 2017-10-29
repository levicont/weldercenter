package com.lvg.weldercenter.se.services.impl

import com.lvg.weldercenter.se.models.CommissionCertification
import com.lvg.weldercenter.se.repositories.CommissionCertificationRepository
import com.lvg.weldercenter.se.services.CommissionCertificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommissionCertificationServiceImpl implements CommissionCertificationService{

    @Autowired
    CommissionCertificationRepository repository

    @Override
    CommissionCertification save(CommissionCertification commissionCertification) {
        return repository.save(commissionCertification)
    }

    @Override
    CommissionCertification get(Long id) {
        return repository.findOne(id)
    }

    @Override
    List<CommissionCertification> getAll() {
        return repository.findAll()
    }

    @Override
    void delete(CommissionCertification commissionCertification) {
        repository.delete(commissionCertification)
    }
}
